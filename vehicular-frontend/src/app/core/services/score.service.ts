import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScoreResponse } from "../models/score-response.model";

@Injectable({
  providedIn: 'root'
})
export class ScoreService {
  private apiUrl = 'http://localhost:8082/api';

  constructor(private http: HttpClient) {
  }

  requestScoreCalculation(plate: string): Observable<void> {
    const params = new HttpParams().set('placa', plate);
    return this.http.post<void>(`${this.apiUrl}/score/calculate`, null, {params});
  }

  getScore(plate: string): Observable<ScoreResponse> {
    return this.http.get<ScoreResponse>(`${this.apiUrl}/score/result/${plate}`);
  }
}
