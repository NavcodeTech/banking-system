import { Component } from '@angular/core';

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent {
  email:string='';
  submitted!: boolean;
  onVerify()
  {
    console.log("email submit",this.email);
  }
}
