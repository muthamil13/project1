import json
    with open("book_catalog.json", "w") as file:
        json.dump(book_catalog, file, indent=4)
    with open("user_data.json", "w") as file:
        json.dump(user_data, file, indent=4)

def add_book():
    title = input("Enter book title: ").strip()
    author = input("Enter author name: ").strip()
    genre = input("Enter book genre: ").strip()
    year = input("Enter publication year: ").strip()
    isbn = input("Enter ISBN number: ").strip()

    if title and author:
        book_catalog[title] = {
            "author": author,
            "details": {
                "genre": genre,
                "year": year,
                "isbn": isbn
            }
        }
        print(f'Book "{title}" by {author} added successfully.')
        save_data()
    else:
        print("Title and author are required fields. Book not added.")

def view_books():
    if not book_catalog:
        print("No books in catalog.")
        return
    print("-" * 50)
    for title, info in book_catalog.items():
        print(f"Title: {title}")
        print(f"Author: {info['author']}")
        details = info['details']
        print(f"Genre: {details['genre']}")
        print(f"Year: {details['year']}")
        print(f"ISBN: {details['isbn']}")
        print("-" * 20)

        if keyword in title.lower() or keyword in info["author"].lower() or keyword in info["details"]["genre"].lower():
            results.append(title)
    if results:
        print("Found books:")
        for book in results:
            print(book)
    else:
        print("No books found matching that keyword.")

def add_user():
    username = input("Enter username: ").strip()
    if username in user_data:
        print("Username already exists.")
        return

    name = input("Enter full name: ").strip()
    email = input("Enter email: ").strip()

    user_data[username] = {
        "name": name,
        "email": email,
        "borrowed_books": []
    }
    print(f'User "{username}" added successfully.')
    save_data()

def view_user_books():
    username = input("Enter your username: ").strip()
    if username not in user_data:
        print("User does not exist.")
        return

    borrowed_books = user_data[username]["borrowed_books"]
    if borrowed_books:
        print(f'Books borrowed by {username}:')
        for book in borrowed_books:
            print(book)
    else:
        print("No books borrowed.")

# Load books and users from file at the start
load_data()


# Main program loop
while True:
    print("\nBook Catalog Menu:")
    print("1. Add Book")
    print("2. View Books")
    print("3. Search Books")
    print("4. Add User")
    print("5. View User Books")
    print("6. Exit")
    choice = input("Enter your choice (1-6): ").strip()

    if choice == '1':
        add_book()
    elif choice == '2':
        view_books()
    elif choice == '3':
        search_books()
    elif choice == '4':
        add_user()
    elif choice == '5':
        view_user_books()
    elif choice == '6':
        break
    else:
        print("Invalid choice. Please try again.")

