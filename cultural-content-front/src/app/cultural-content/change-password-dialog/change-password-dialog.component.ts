import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {ToastrService} from 'ngx-toastr';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

export interface DialogData {
    animal: string;
    name: string;
}

@Component({
  selector: 'app-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.scss']
})
export class ChangePasswordDialogComponent implements OnInit {
    form: FormGroup;

    constructor(
        public dialogRef: MatDialogRef<ChangePasswordDialogComponent>,
        private fb: FormBuilder,
        private userService: UserService,
        private toastr: ToastrService
    ) {
        this.form = this.fb.group({
        oldPassword : [null, Validators.required],
        newPassword : [null, Validators.required],
        newPasswordRepeat : [null, Validators.required]
    });
    }

    ngOnInit(): void {
    }

    submit(): void {
        const passwordDTO: any = {};
        passwordDTO.oldPassword = this.form.value.oldPassword;
        passwordDTO.newPassword = this.form.value.newPassword;
        passwordDTO.newPasswordRepeat = this.form.value.newPasswordRepeat;
        if (passwordDTO.newPassword !== passwordDTO.newPasswordRepeat) {
            return;
        }

        this.userService.changePassword(passwordDTO).subscribe(
            result => {
                this.toastr.success(result.message);
            }, error => {
                console.log(error.error);
            }
        );
    }

    onNoClick(): void {
        this.dialogRef.close();
    }



}
