import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginServiceService } from './service/login-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private loginService:LoginServiceService, private router: Router) {}

  canActivate(): boolean {
    if (this.loginService.getToken()) {
      console.log(this.loginService.getToken());
      console.log("checking");
      return true;
    } else {
      this.router.navigate(['/login']); // Redirect to login page if not authenticated
      return false;
    }
  }
}
