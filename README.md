## Chess Demo

### Structure

folders `src/main/java` contain : 

`chess`<b>`model`</b>  
 - `piece` - abstract class that contains data representation of piece and operations with it
   - `pawn` - extends `piece` class by specification of pawn properties
 - `model` - contains data representations of objects in the game (pieces, board...) 
     - together with basic logic operations with them (which was subsequently moved to the controller in the final version)

`chess`<b>`view`</b>  
 - provides <b>gui</b> update operations
   - which is made up of `JButton` array (from `javax.swing` library) and `ActionListener`s (`java.awt.event`) on single JFrame (`javax.swing`) 
  
`chess`<b>`controller`</b>
 - contains game-initialization methods
   - in the final version it contained the logical operations of the game

`Start` :arrow_forward:
 - by running the main method you can try the <i>chess demo</i> yourself
   - initializes model, view, controllel and sets new game

<hr />

<div align="center">
  <sub>Although I managed to submit the final fully functional version in the term, I decided to publish only the demo of the application (due to the lack    of knowledge of OOP in 2nd semester - which could have been seen in the final code)</sub>
</div>
