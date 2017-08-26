import { Injectable } from '@angular/core';
import {AppConst} from '../constants/app-const';
import {Router} from '@angular/router';
import {Headers, Http} from '@angular/http';
import {UserPayment} from '../models/user-payment';


@Injectable()
export class PaymentService {
  private serverpath:string= AppConst.serverPath;

  constructor(private http:Http) { }

  newPayment(payment: UserPayment){
    let url=this.serverpath+"/payment/add";
    let tokenHeader=new Headers({
      'content-type':'application/json',
      'x-auth-token':localStorage.getItem('xAuthToken'),
    });

    return this.http.post(url, JSON.stringify(payment), {headers:tokenHeader});
  }

  getUserPaymentList(){
        let url=this.serverpath+"/payment/getUserPaymentList";
    let tokenHeader=new Headers({
      'content-type':'application/json',
      'x-auth-token':localStorage.getItem('xAuthToken'),
    });

    return this.http.get(url, {headers:tokenHeader});
  }

  removePayment(id: number){
    let url=this.serverpath+"/payment/remove";
    let tokenHeader=new Headers({
      'content-type':'application/json',
      'x-auth-token':localStorage.getItem('xAuthToken'),
    });

    return this.http.post(url,id, {headers:tokenHeader});
  }
  setDefaultPayment(id: number){
    let url=this.serverpath+"/payment/setDefault";
    let tokenHeader=new Headers({
      'content-type':'application/json',
      'x-auth-token':localStorage.getItem('xAuthToken'),
    });

    return this.http.post(url,id, {headers:tokenHeader});
  }


}
