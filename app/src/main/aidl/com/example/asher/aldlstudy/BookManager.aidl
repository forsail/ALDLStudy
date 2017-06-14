// BookManager.aidl
package com.example.asher.aldlstudy;

// Declare any non-default types here with import statements
import com.example.asher.aldlstudy.Book;

interface BookManager {
     List<Book> getBooks();
     void addBook(in Book book);
}
