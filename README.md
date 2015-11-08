# Grid_Engine
2015 CS Final

Controls:
  Toggle HUD            H
  Toggle Inventory      I
  Open Console/Pause    ENTER

To cast a spell, either
  type 
    "cast <spellnumber> <xcoord> <ycoord>" + press space to preview result
    or
    "cr <spellnumber> <relative xcoord> <relative ycoord> + press space to preview result
  or
  open inventory > l-click spell icon > l-click target square > press space to preview result
To move, either
  type
    "move <xcoord> <ycoord>" + space to preview path
    or
    "mr <relative xcoord> <relative ycoord>" + space to preview path
  or
  r-click target square

Display:
  HUD: [HEART] <health>  [SHIELD] <armor> [CLOCK] <current gametick> <paused/unpaused>    <selected xcoord> <selected ycoord>
  Screen:
    CYAN      Enemy movement path
    RED       Player movement path
    DBLUE     Enemy spell path
    PURPLE    Player spell path
    GREEN     Player spell preview
    LBLUE     L-click selected square (coordinates shown top right)
    LGREEN    R-click selected square (coordinates shown top right)
