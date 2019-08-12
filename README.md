# Ohmer-2010, Ohmer X, The Rollover Bot

### History

 Date | Controller | IDE | Description
 -----|------------|-----|------------
 Jan 2010 | cRIO | ?? | Original - Lost to history
 Jan 2016 | roboRIO | Eclipse | Re-constituded by Frank
 Jan 2017 | roboRIO | Eclipse | Rework by Frank to TR86 standard
 Jan 2019 | roboRIO | VSCode | Rework by Joey & Anthony to new First standard

### Description
The object of the game was to kick balls down the field and into a goal(s).  However, there was a 8” tall by 12” wide barrier in the middle of the field.  So they had to kick balls over the barrier then cross the barrier and then kick (corral) the balls in the goal.

The teams solution was to have a bot that could kick a ball high, to get balls over the barrier, by lowering the kicker.  And kick the balls low, for scoring, by raising the kicker,

To get over the barrier, they climb over it and flip upside down then use a bot flipper to right the bot.  During testing they found if they did it fast, it continued to roll into an upright position.  Sometimes.

### Hardware
* Robot Hardware
    * roboRIO
    * Router
    * VRM, Voltage Regulator Module
    * PDP (old), Power Distribution Panel
* Tank Drive
    * (2) Lefthand motors (2 Victor ESC’s - 1 PWM signal, y-Cable)
    * (2) Righthand motors ( 2 Victor ESC’s - 1 PWM signal, y-cable)
    * (3)Joysticks - 2 driver & 1 co-driver
    * Driver JS's used for Left & Right Wheels
* Flipper Note: the Flipper does not have end switches and the motor is powerful enough to break things.  When operating it, caution is advised.
    * (2) motors (2 Victor ESC’s - 1 PWM signal, y-cable)
    * (1) Co-driver JS fwd to extend Flipper, bkwd to retract
* Kicker Spinner  Note: Needs to spin in one direction, under.
    * (1) motor (Victor 884 ESC)
    * (1) Co-driver Button to toggle Kicker spin On/Off
* Kicker Positioner, Pivot  Note: end switches MUST not be exceeded, things will break.  Again caution advised.
    * (1) motor (Victor ESC)
    * (1) Pivot Lower End Switch
    * (1) Pivot Upper End Switch
    * (1) Button to Lower pivot position
    * (1) Button to Raise pivot position
    * (1) Opt. Use CoDriverJS, fwd is raise, bkwrd is lower 

### Joystick Assignments

![Image of Joystick](https://github.com/teamresistance/RolloverBot-2019/blob/master/RO/images/joystick360.jpg)

 Tag | Values | JS1 | JS2 | JS3
 ----|--------|-----|-----|----
  X1 | -1.0/1.0 | L-Wheels Fwd/Bkwd | R-Wheels Fwd/Bkwd | Flip Bar Ext/Retr
  Y1 | -1.0/1.0 | n/a | n/a | n/a | n/a
  X2 | -1.0/1.0 | n/a | n/a | Kicker Pivot Raise/Lower
  Y2 | -1.0/1.0 | n/a | n/a | n/a | n/a
  Tgr 1 | opn/cls | n/a | n/a | CV Drive to target
  Btn 2 | opn/cls | n/a | n/a | Toggle Kicker On/Off

