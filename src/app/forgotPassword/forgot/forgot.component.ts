import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/project.service';

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent {
  @Input()
  email:string='';
  submitted!: boolean;
  
  
  constructor(private router: Router,private prgService:ProjectService) {
    this.submitted=false;
  }
  errorMsg:string="";
  message:string="";
  onSendOtp()
  {
    console.log(this.email);

    this.prgService.handleForgot(this.email).subscribe(
      response=>
      {
        console.log(response);
        //this.message=response
        this.router.navigate(['/verify_otp']);
      },
      error =>
      {
        console.log(error.error.message)
        console.log(error.message+"  .....")
        console.log("error msg is... "+JSON.stringify(error.error));
        
        this.errorMsg=error.error.message
      });
  }
}
