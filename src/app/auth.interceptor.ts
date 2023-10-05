import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginServiceService } from './service/login-service.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private loginService:LoginServiceService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    const token = this.loginService.getToken();
    if (token) {
      const clonedRequest = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(clonedRequest);
    }
    else{
    return next.handle(request);
    }
  }
}
