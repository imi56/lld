### Observer design pattern
I have implemented overser design pattern where on creation of a user I am sending an email.

- 2 interfaces
  - 1 for the subject - UserSubject has 3 method register, remove, notify
  - 1 for observer - UserObserver has 1 action method example onCreate

The UserService implements UserSubject
The Email service implements UserObserver