# CLI Snake
> Object-oriented programming homework

The program is WIP as of yet.

## To-dos:
- [x] Menu layout, buttons
  - [x] Start new game
  - [x] Load saved game
  - [x] Set field size
  - [x] Custom field maker
  - [x] High scores
    - [x] Comparable (order by name / points)
    - [x] Save high score
  - [x] Close
- [x] In-game functionality
  - [x] Draw field and elements
  - [x] Draw snake (moving)
  - [x] Control snake
    - [x] Scheduled move (input only sets direction)
    - [x] Handle collision
  - [x] Apple-eating mechanism
    - [x] Apple regeneration
    - [x] Snake growing
  - [x] Point system
  - [x] In-game pause
    - [x] Save game
    - [x] Resume
    - [x] Exit
- [ ] Add documentation
  - [ ] Javadoc
  - [ ] User manual
  - [ ] UML
- [ ] JUnit tests
- [x] Bugfixes
  - [x] Snake body begints to disappear in incorrect order after loading save
    - Save body part's index and add parts to body list in order?
- [ ] Cosmetics
  - [ ] Move cursor to 0 position instead of clearing whole display
    (to avoid flickering)
  - [x] Title logo
  - [ ] Cooler menu (formatter?)
