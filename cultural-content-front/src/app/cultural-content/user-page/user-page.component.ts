import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ImageService} from '../../services/image.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {MatDialog} from '@angular/material/dialog';
import {ChangePasswordDialogComponent} from '../change-password-dialog/change-password-dialog.component';

class ImageSnippet {
    constructor(public src: string, public file: File) {}
}

class CurrentUser {
    constructor(
        public firstName: string,
        public lastName: string,
        public email: string
    ) {}
}

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class UserPageComponent implements OnInit {
    selectedFile: ImageSnippet | undefined;
    currentUser: CurrentUser | undefined;
  constructor(
      private imageService: ImageService,
      private dialog: MatDialog
  ) { }

  ngOnInit(): void {
      const token = localStorage.getItem('user');
      const jwt: JwtHelperService = new JwtHelperService();
      if (!token) {
          return;
      }
      const info = jwt.decodeToken(token);
      this.currentUser = new CurrentUser(info.firstName, info.lastName, info.sub);
  }

    processFile(imageInput: any): void {
        const file: File = imageInput.files[0];
        const reader = new FileReader();
        reader.addEventListener('load', (event: any) => {
            this.selectedFile = new ImageSnippet(event.target.result, file);
            this.imageService.uploadImage(this.selectedFile.file).subscribe(
                (res) => {
            },
                (err) => {
            });
        });
        reader.readAsDataURL(file);
    }

    changePasswordDialog(): void {
      const dialogRef = this.dialog.open(ChangePasswordDialogComponent, {
          width: '500px'
      });

      dialogRef.afterClosed().subscribe(result => {
          // dodaj nesto
      });
    }
}
