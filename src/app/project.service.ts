import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";

import { User } from "./model/User";
import { Observable } from "rxjs/internal/Observable";
import { UserAccount } from "./model/UserAccount";
import { LoanData } from "./model/loan-data";
//import { RegUserService } from "./service/reg-user.service";
import { TransactionModel } from "./model/transaction-model";
import { catchError, throwError } from "rxjs";
import { CreditModel } from "./model/credit-model";
import { CookieService } from "ngx-cookie-service";


@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private baseUrl = 'http://localhost:8085/api/users'; // Update with your backend URL
  user:User|null=null;
  
  username1:string='';
  constructor(private http: HttpClient,private ckService:CookieService) { }

  register(user: User): Observable<any> {
    console.log('register called in service ');
    const url = `${this.baseUrl}/register`;
    console.log('url ' + url);
    console.log('user ' + JSON.stringify(user));
    this.username1=user.username;
    return this.http.post(url, (user));
  }

  login(formData: FormData): Observable<any> {
    const url = `${this.baseUrl}/login`;
    console.log(formData);
    return this.http.post(url, formData);
  }
  createAccount(account: UserAccount): Observable<any> {

    console.log('userAccoount  called in service ' + account);
    const usr=localStorage.getItem("username");
    const url = `${this.baseUrl}/accounts/createaccount/${usr}`;
    console.log('url ' + url);
    console.log('user ' + JSON.stringify(account));
    return this.http.post(url, (account));
  }
  updateAccount(account: UserAccount): Observable<any> {

    console.log('userAccoount  called in service ' + account);
    const usr=localStorage.getItem("username");
    const url = `${this.baseUrl}/accounts/update/${usr}`;
    console.log('url ' + url);
    console.log('user ' + JSON.stringify(account));
    return this.http.put(url, (account));
  }
  
  handleTrans(tra: TransactionModel): Observable<string> {
    console.log('userAccoount  called in service ' + tra);
    const usr=localStorage.getItem("username");
    const url = `${this.baseUrl}/transaction/debit/${usr}`;
    console.log('url ' + url);
    console.log('transaction ' + JSON.stringify(tra));
    const headers=new HttpHeaders(
      {
        'Content-Type' : 'application/json'
      });
    return this.http.post<string>(url, (tra),{headers:headers})
  }
  loanBackend(data: LoanData): Observable<any> {

    console.log('userAccoount  called in service ' + data);
    const url = `${this.baseUrl}/loan/loanrequest`;
    console.log('url ' + url);
    console.log('user ' + JSON.stringify(data));
    return this.http.post(url, (data));
  }
  getAccDetail():Observable<UserAccount>{
    
    const  usr=localStorage.getItem("username");
    const url=`${this.baseUrl}/accounts/${usr}`;
    console.log("user name "+this.username1);
    console.log( JSON.stringify(this.http.get(url)));
    return this.http.get<UserAccount>(url);
  }
  getUser():Observable<User>{
    const  usr=localStorage.getItem("username");
    const url=`${this.baseUrl}/${usr}`;
    console.log( JSON.stringify(this.http.get(url)));
    return this.http.get<User>(url);
  }
  handleCredit(credit1: CreditModel): Observable<string> {
    //console.log('userAccoount  called in service ' + credit1);
    const usr=localStorage.getItem("username");
    const url = `${this.baseUrl}/transaction/credit/${usr}`;
    console.log('url ' + url);
    console.log('transaction ' + JSON.stringify(credit1));
    const headers=new HttpHeaders(
      {
        'Content-Type' : 'application/json'
      });
    return this.http.post<string>(url, (credit1),{headers:headers})
  }
  getTrans():Observable<TransactionModel[]>{
    
    const  usr=localStorage.getItem("username");
    const url=`${this.baseUrl}/transaction/debit/${usr}`;
    console.log("user name "+this.username1);
    console.log( JSON.stringify(this.http.get(url)));
    return this.http.get<TransactionModel[]>(url);
  }
  getCredit():Observable<CreditModel[]>{
    
    const  usr=localStorage.getItem("username");
    const url=`${this.baseUrl}/transaction/credit/${usr}`;
    console.log("user name "+this.username1);
    console.log( JSON.stringify(this.http.get(url)));
    return this.http.get<CreditModel[]>(url);
  }
  getLastTrans():Observable<TransactionModel>{
    const  usr=localStorage.getItem("username");
    const url=`${this.baseUrl}/transaction/${usr}`;
    console.log("user name "+this.username1);
    console.log( JSON.stringify(this.http.get(url)));
    return this.http.get<TransactionModel>(url);
  }
  deleteAccDetail():Observable<string>
  {
    const usr=localStorage.getItem("username");
    const url = `${this.baseUrl}/accounts/delete/${usr}`;
    console.log('url ' + url);
    return this.http.delete<string>(url);
  }
  getLoanData():Observable<LoanData[]>{
    
    const  usr=localStorage.getItem("username");
    const url=`${this.baseUrl}/loan/${usr}`;
    console.log("user name "+this.username1);
    console.log( JSON.stringify(this.http.get(url)));
    return this.http.get<LoanData[]>(url);
  }
  handleForgot(email:string):Observable<string>{

    console.log('forgot  called in service ' + email);
    const url = `${this.baseUrl}/send_otp`;
    const headers=new HttpHeaders().set('myemail',email);
    const data=["myemail",email];
    this.ckService.set('myemail',email);
    console.log('url ' + url);
    //console.log('user ' + JSON.stringify(email));
    return this.http.get<string>(url,{headers});
  }
  handleOtp(otp:string):Observable<string>{

    console.log('otp verify  called in service ' + otp);
    const url = `${this.baseUrl}/verify_otp`;
    const headers=new HttpHeaders().set('myotp',otp);
    console.log('url ' + url);
    console.log('user ' + JSON.stringify(otp));
    return this.http.get<string>(url, { headers });
  }
  handlePassword(password:string){

    console.log('change password  called in service ' + password);
    const url = `${this.baseUrl}/change_password`;
    const headers=new HttpHeaders().set('password',password);
    console.log('url ' + url);
    console.log('user ' + JSON.stringify(password));
    return this.http.get(url, {headers});
  }
}