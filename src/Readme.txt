***************************************************************************************************
------------------------------------------- Grid Game ---------------------------------------------
[[                                                                                               ]]
[[                By: Christian Gutowski, Michael Fischler, and Herman Sahani                    ]]
[[                                                                                               ]]
---------------------------------------------------------------------------------------------------
***************************************************************************************************


Setup and Running the Game
---------------------------------------------------------------------------------------------------

Click the Grid Game.jar file to start the game, make sure the resources folder is in the same
directory! (Otherwise the game will not start)

How to Play
---------------------------------------------------------------------------------------------------

# Universal Controls

The 'Esc' key can be used to exit the game to the main menu, to exit the command line/terminal, 
and to exit the inventory.

# The HUD (top gray bar in-game, togglable by default with the 'H' key, configurable in settings)

The HUD displays all the player's current stats, in addition, at the rightmost side of the HUD
the absolute coordinates that were last clicked on the screen are shown. When the terminal is
open, a left click on the map sends these coordinates to the terminal.

# The Terminal (bottom gray bar in-game, enter with the 'Enter' key, exit with 'Esc')

The terminal allows the user to control their player. As previously mentioned, a left click
while this is open enters the absolute coordinates of the point clicked (with a space
seperating the two points).
	
Proper Syntax: [command name] [space] [arguments seperated by spaces]

Available Commands:
	cast = cast a spell, arguments are [spell id] [abs x] [abs y]
	cr   = cast a spell to a relative point, arguements are [spell id] [relative x] [relative y]
	move = move the player, arguments are [abs x] [abs y]
	mr   = move the player to a relative point, arguments are [relative x] [relative y]
	
Spell IDs can be found by opening the inventory (see next section) and hovering the mouse over a
spell.

Use the enter key to submit commands. Hitting the spacebar after filling out a command instead of
hitting enter will preview the command on the map. Hitting enter after that will still submit the 
command.

# The Inventory (opened by default with the "I" key, configurable in settings)

The inventory contains a list of all spells, along with how many of each the player has. Hovering
over a spell shows its id. If you click a spell, the command line fills out the command for you,
except for the coordinates. You can then left click somewhere on the map to set the coordinates,
then hit enter to cast the spell.

# Enemies

At the moment, there is only one enemy: the skeleton. It follows the player when it sees you, and
will attack when in range. It can be killed through attacks and can also hurt the player.

Cyan highlights indicate enemy pathing, and blue highlights indicate enemy attacks.

# Settings Page

The settings page can be used to change what key toggles the inventory (which can also be exited
using the 'Esc' key) and which key toggles the HUD.

# About Page

Provides sources that were used in the making of this game.

# Notes on current progress of game

Player kind of can currently die, and player also has unlimited of all spells. New games can be 
made by quiting the program and re-opening it.

Glossary
---------------------------------------------------------------------------------------------------

absolute - absolute position in the world
relative - relative to the player's position, up is -x, down is +x, left is -y, right is +y
spell    - available in the inventory, there are many different types. these are the abilities, 
           defensive or offensive, of the player and the creatures he/she encounters.
spell id - a number assigned to each spell, found when hovering over a spell in the inventory


***************************************************************************************************
-                                Written by Michael Fischler                                      -
***************************************************************************************************