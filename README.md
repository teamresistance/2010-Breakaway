# RolloverBot-2010, Ohmer X

The object of the game was to kick balls down the field and into a goal(s).  However, there was a 8” tall by 12” wide barrier in the middle of the field.  So they had to kick balls over the barrier then cross the barrier and then kick (corral) the balls in the goal.

The teams solution was to have a bot that could kick a ball high, to get balls over the barrier, by lowering the kicker.  And kick the balls low, for scoring, by raising the kicker,

To get over the barrier, they climb over it and flip upside down then use a bot flipper to right the bot.  During testing they found if they did it fast, it continued to roll into an upright position.  Sometimes.

To accomplish this they have the following controls:
* ●	Hardware
* ○	RoboRIO
 * ○	Router
 * ○	VRM (or maybe able to get regulated 12vdc from the old PDP)
 *○	PDP (old)
* ●	Tank Drive
 * ○	(2) Lefthand motors (2 Victor ESC’s - 1 PWM signal)
 * ○	(2) Righthand motors ( 2 Victor ESC’s - 1 PWM signal)
 * ○	Joysticks
* ●	Flipper Note: the Flipper does not have end switches and the motor is powerful enough to break things.  When operating it, caution is advised.
 * ○	(2) motors ( 2 Victor ESC’s - 1 PWM signal.  However, there is a wiring issue and we have converted to 2 PWM signals)
 * ○	(1) Button to extend Flipper
 * ○	(1) Different button to retract Flipper
* ●	Kicker Spinner  Note: Needs to spin in one direction, under.
 * ○	(1) motor (Victor 884 ESC)
 * ○	(1) Button to toggle Kicker spin On/Off
* ●	Kicker Positioner, Pivot  Note: end switches MUST not be exceeded, things will break.  Again caution advised.
 * ○	(1) motor (Victor ESC)
 * ○	(1) Pivot Lower End Switch
 * ○	(1) Pivot Upper End Switch
 * ○	(1) Button to Lower pivot position
 * ○	(1) Button to Raise pivot position
 * ○	(1) Opt. Use CoDriverJS, fwd is raise, bkwrd is lower 
