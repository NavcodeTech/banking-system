import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ProjectService } from 'src/app/project.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent {
  
  @Input()
  password:string='';
  submitted!: boolean;
  
  
  constructor(private router: Router,private prgService:ProjectService) {
    this.submitted=false;
  }
  errorMsg:string="";
  message:string="";
  changePassword()
  {
    console.log(this.password);
    this.prgService.handlePassword(this.password).subscribe(
      response=>
      {
        console.log(response);
        //this.message=response
        this.router.navigate(['/login']);
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
