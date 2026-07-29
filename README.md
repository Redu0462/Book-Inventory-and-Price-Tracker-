# Book Inventory and Price Tracker

A console-based Java application for managing a bookstore inventory system, built as a group project (Group of 5 members) applying core Object-Oriented Programming principles.

## Features

- Add, search, update, and delete books (Physical Books and EBooks)
- Automatic price history tracking with timestamps
- Sort and group books by category
- Save and load inventory data to/from CSV file
- Input validation to prevent crashes

## OOP Concepts Applied

- Inheritance and Polymorphism (abstract Book class, PhysicalBook/EBook subclasses)
- Encapsulation (private fields with getters/setters)
- Interfaces (Displayable)
- Enums (BookCategory)
- Generics (Inventory<T extends Book>)
- Custom Exceptions (BookNotFoundException, DuplicateISBNException)
- File I/O (CSV read/write via FileHandler)

## Project Structure

src/model - Book, PhysicalBook, EBook, BookCategory, Displayable
src/exception - Custom exception classes
src/management - Inventory, PriceTracker, BookSorter
src/File - FileHandler
src/Main - Main.java (entry point)

## My Contribution

Collaborated in a team of 5 to design and implement this system, contributing to application logic, debugging, and project coordination.

## Team

Group of 5 members