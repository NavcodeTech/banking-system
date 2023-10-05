import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';

import { RegisterComponent } from './register/register.component';

import { HomeComponent } from './home/home.component';

import { UpdateComponent } from './accounts/update/update.component';

import { TransactionComponent } from './transaction/transaction.component';

import { LoanComponent } from './loan/loan.component';

import { PassbookComponent } from './passbook/passbook.component';

import { AccountsComponent } from './accounts/accounts.component';

import { CreateAccountComponent } from './accounts/create-account/create-account.component';

import { CreditSlipComponent } from './transaction/credit-slip/credit-slip.component';

import { CreditChequeComponent } from './transaction/credit-cheque/credit-cheque.component';

import { DebitSlipComponent } from './transaction/debit-slip/debit-slip.component';

import { DebitChequeComponent } from './transaction/debit-cheque/debit-cheque.component';

import { TransactionDetailsComponent } from './passbook/transaction-details/transaction-details.component';

import { AccountSummaryComponent } from './passbook/account-summary/account-summary.component';

import { LoanRequestComponent } from './loan/loan-request/loan-request.component';

import { LoanDisbursalComponent } from './loan/loan-disbursal/loan-disbursal.component';

import { AdminComponent } from './admin/admin.component';

// import { DeleteComponent } from './accounts/delete/delete.component';

import { Admin1Component } from './admin/admin1/admin1.component';

import { DashboardComponent } from './admin/admin1/dashboard/dashboard.component';

import { UsermanagementComponent } from './admin/admin1/dashboard/usermanagement/usermanagement.component';
import { AccountManagementComponent } from './admin/admin1/dashboard/accountmanagement/accountmanagement.component';
import { TransactionManagementComponent } from './admin/admin1/dashboard/transaction-management/transaction-management.component';
import { LoanmanagementComponent } from './admin/admin1/dashboard/loanmanagement/loanmanagement.component';
import { ForgotComponent } from './forgotPassword/forgot/forgot.component';
import { VerifyOtpComponent } from './forgotPassword/verify-otp/verify-otp.component';
import { ChangePasswordComponent } from './forgotPassword/change-password/change-password.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [

 { path: '', redirectTo: '/login', pathMatch: 'full' },

 { path: 'login', component: LoginComponent },

 { path: 'register', component: RegisterComponent },

 { path: 'home', component: HomeComponent,canActivate: [AuthGuard] },

 { path: 'accounts/update', component: UpdateComponent,canActivate: [AuthGuard] },

 { path: 'accounts/createaccount', component: CreateAccountComponent,canActivate: [AuthGuard] },

//  { path: 'accounts/delete', component: DeleteComponent,canActivate: [AuthGuard] },

 { path: 'transaction', component: TransactionComponent,canActivate: [AuthGuard] },

 { path: 'transaction/creditslip', component: CreditSlipComponent,canActivate: [AuthGuard] },

 { path: 'transaction/creditcheque', component: CreditChequeComponent,canActivate: [AuthGuard] },

 { path: 'transaction/debitslip', component: DebitSlipComponent,canActivate: [AuthGuard] },

 { path: 'transaction/debitcheque', component: DebitChequeComponent,canActivate: [AuthGuard] },

 { path: 'loan', component: LoanComponent,canActivate: [AuthGuard] },

 { path: 'loan/loanrequest', component: LoanRequestComponent,canActivate: [AuthGuard] },

 { path: 'loan/loandisbursal', component: LoanDisbursalComponent,canActivate: [AuthGuard] },

 { path: 'passbook', component: PassbookComponent,canActivate: [AuthGuard] },

 { path: 'passbook/transactiondetails', component: TransactionDetailsComponent,canActivate: [AuthGuard] },

 { path: 'passbook/accountsummary', component: AccountSummaryComponent,canActivate: [AuthGuard] },

 { path: 'accounts', component: AccountsComponent,canActivate: [AuthGuard] },

 { path: 'admin', component: AdminComponent },

 { path: 'admin/admin1', component: Admin1Component },

 { path: 'admin/admin1/dashboard', component: DashboardComponent },

 { path: 'admin/admin1/dashboard/usermanagement', component: UsermanagementComponent},

 { path: 'admin/admin1/dashboard/accountmanagement', component: AccountManagementComponent},
 { path: 'admin/admin1/dashboard/transactionmanagement', component: TransactionManagementComponent},
 { path: 'admin/admin1/dashboard/loanmanagement', component: LoanmanagementComponent },

 { path: 'forgot', component:ForgotComponent,},
 { path: 'verify_otp', component:VerifyOtpComponent,},
 { path: 'change_password', component:ChangePasswordComponent,}


];

@NgModule({

 imports: [RouterModule.forRoot(routes)],

 exports: [RouterModule],

})

export class AppRoutingModule {}