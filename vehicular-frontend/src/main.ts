import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { routes } from './app/app.routes';

import { AppComponent } from './app/app.component';
import { provideRouter } from "@angular/router";

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),
    importProvidersFrom(FormsModule),
    provideHttpClient(),
    provideRouter(routes)
  ]
}).catch(err => console.error(err));
