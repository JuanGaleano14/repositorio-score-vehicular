import { Component } from '@angular/core';
import { ScoreService } from '../../core/services/score.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";

@Component({
  selector: 'app-score-checker',
  standalone: true,
  imports: [FormsModule, CommonModule, HttpClientModule],
  templateUrl: './score-checker.component.html',
  styleUrls: ['./score-checker.component.css']
})
export class ScoreCheckerComponent {
  plate: string = '';
  score: number | null = null;
  error: string | null = null;
  isLoading: boolean = false;

  constructor(private scoreService: ScoreService) {
  }

  // Método para validar la placa
  isPlateValid(plate: string): boolean {
    const platePattern = /^[A-Z]{3}[0-9]{3}$/;
    return platePattern.test(plate);
  }

  clearMessage() {
    this.error = null;
    this.score = null;
  }

  onSubmit() {
    if (this.isPlateValid(this.plate)) {
      this.error = null;
      this.score = null;
      this.scoreService.requestScoreCalculation(this.plate)
        .subscribe({
          next: (response: any) => {
            if (response) {
              this.requestForScore();
            } else {
              this.error = 'No se recibió una respuesta válida del servidor';
            }
          },
          error: (err) => {
            console.error(err);
            this.error = 'Error en la solicitud de cálculo';
          }
        });
    } else {
      alert('Formato de placa inválido. Debe ser ABC123.');
    }
  }

  requestForScore() {
    this.isLoading = true;
    setTimeout(() => {
      this.scoreService.getScore(this.plate).subscribe({
        next: (result) => {
          this.score = result.score;
          this.error = result.message;
          this.isLoading = false;
        },
        error: (err) => {
          this.error = 'Error obteniendo el puntaje';
          this.isLoading = false;
        }
      });
    }, 1000); // Simular cargando
  }
}
