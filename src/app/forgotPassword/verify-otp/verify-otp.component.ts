import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/project.service';

@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.css']
})
export class VerifyOtpComponent {
  @Input()
  otp:string='';
  submitted!: boolean;
  
  
  constructor(private router: Router,private prgService:ProjectService) {
    this.submitted=false;
  }
  errorMsg:string="";
  message:string="";
  onVerifyOtp()
  {
    console.log(this.otp);

    this.prgService.handleOtp(this.otp).subscribe(
      response=>
      {
        console.log(response);
        //this.message=response
        this.router.navigate(['/change_password']);
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
