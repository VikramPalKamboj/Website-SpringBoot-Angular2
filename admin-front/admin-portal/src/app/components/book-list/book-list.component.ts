import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {LoginService} from '../../services/login.service';
import {GetBookListService} from '../../services/get-book-list.service';
import {RemoveBookService} from '../../services/remove-book.service';

import {Book} from '../../model/book';

import {MdDialogRef, MdDialog} from '@angular/material';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  private selectedBook: Book;
  private checked:boolean;
  private bookList:Book[];
  private allChecked: boolean;
  private removeBookList: Book[]= new Array();


  constructor(

    private getBookListService:GetBookListService,
    private router:Router,
    public dialog:MdDialog,
    public removeBookService:RemoveBookService
  )
   { }

   onSelect(book: Book){
    this.selectedBook=book;
    this.router.navigate(['/viewBook',this.selectedBook.id ]);
   }

   // for removing only one or more particular items 
   updateRemoveBookList(checked:boolean, book:Book){
      if(checked){
        this.removeBookList.push(book);
      }
      else{
        this.removeBookList.slice(this.removeBookList.indexOf(book), 1);
      }
   }

   // for removing all items selectd
   updateSelected(checked:boolean){
      if(checked)
      {
        this.allChecked=true;
        this.removeBookList=this.bookList.slice();
      }
      else{
        this.allChecked=false;
        this.removeBookList=[];
      }
   }

   // open Dialog to ensure that you want to delete this item.
   openDialog(book:Book){
    let dialogRef=this.dialog.open(DialogResultExampleDialog);
    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result);
        if(result=="yes"){
          this.removeBookService.sendBook(book.id).subscribe(
            res=>{
              console.log(res);
              location.reload();
            },
            error=>{
              console.log(error);

            }

          );
        }
      }
    );
  }

  removeSelectedBooks(){
     let dialogRef=this.dialog.open(DialogResultExampleDialog);
    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result);
        if(result=="yes"){

          for(let book of this.removeBookList){
            this.removeBookService.sendBook(book.id).subscribe(
            res=>{
           
            },
            error=>{
              console.log(error);

            }

          );
          }
          location.reload();
        }
      }
    );

  }

  ngOnInit() {

    this.getBookListService.getBookList().subscribe(
      res=>{
        console.log(res.json());
        this.bookList=res.json();
      },
      error => {
        console.log(error);
      }
    )
  }

}




  @Component({
  selector:"dialog-result-example-dialog",
  templateUrl:"dialog-result-example-dialog.html"
})
export class DialogResultExampleDialog {
   constructor(public dialogRef:MdDialogRef<DialogResultExampleDialog>){}
}

