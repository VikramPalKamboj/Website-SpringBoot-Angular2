import { Component, OnInit } from '@angular/core';
import {GetBookService} from '../../services/get-book.service';
import {UploadImageService} from '../../services/upload-image.service';
import {Params, ActivatedRoute, Router} from '@angular/router';
import {Book} from '../../model/book';
import {EditBookService} from '../../services/edit-book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  public bookId:number;
  public book:Book= new Book();
  public bookUpdated:boolean;
  constructor(
    private uploadImageService:UploadImageService,
    private route:ActivatedRoute,
    private getBookService:GetBookService,
    private editBookService:EditBookService 
  ) { }
 
 onSubmit(){
   this.editBookService.sendBook(this.book).subscribe(
     data=>{
       this.uploadImageService.modify(JSON.parse(JSON.parse(JSON.stringify(data))._body).id);
       this.bookUpdated=true;

     },
     error => console.log(error)
   )
 }
  ngOnInit() {
    this.route.params.forEach((params:Params)=>{
      this.bookId=Number.parseInt(params['id']);

    });

    this.getBookService.getBook(this.bookId).subscribe(
      res=>{
        this.book=res.json();

      },
      error=> console.log(error)
    )
  }

}
