# Book Inventory and Price Tracker

 A console-based Java application for managing a bookstore inventory system - built as a group project (Group of 5 members) applying core Object-Oriented Programming principles.

## Features
- Add, search, update, and delete books (Physical and EBooks)
- Automatic price history tracking with timestamp
- sort and group books by category
- save/load inventory data to/from CSV file
- Full input validation to prevent crashes

## OOP Concepts Applied
- Inheritance and polymorphism (abstract Book class, PhysicalBook/EBook subclasses)
- Encapsulation (private fields with getters/setters)
- Interfaces (Displayable)
- Enums (BookCategory)
- Generics (Inventory<T extends Book>)
- Custom Exceptions (BookNotFoundException, DuplicateISBNException)
- File I/O (CSV read/write via FileHandler)

## Project Structure
 src/model - Book, PhysicalBook, EBook, BookCatagory, Displayable
src/exception - Custom exception classes
src/management - Inventory, PriceTracker, BookSorter
src/File - FileHandler
src/Main - Main.java (entry point)

## Team
 Group project - Group of 5 members
