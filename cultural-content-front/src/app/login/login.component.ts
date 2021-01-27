import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthenticationService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.form = this.fb.group({
      email : [null, Validators.required],
      password : [null, Validators.required]

    });
  }

  ngOnInit(): void {
  }

  submit(): void {
    const auth: any = {};
    auth.email = this.form.value.email;
    auth.password = this.form.value.password;

    this.authService.login(auth).subscribe(
      result => {
        const token: any = result;
        localStorage.setItem('user', token.accessToken);
        localStorage.setItem('expiresIn', token.expiresIn);
        this.router.navigate(['']);
        location.reload();
      },
      error => {
          this.toastr.error(error.error);
      }
    );
  }

}
