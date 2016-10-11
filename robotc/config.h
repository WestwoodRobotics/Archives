#ifndef RCONFIG
#define RCONFIG

// Deadband keeps small signals from being sent from the joysticks
#define DEADBAND_LIM 10.0
#define DEADBAND(x) (abs(x) > DEADBAND_LIM ? x : 0)

// Defines the axes with easier-to-remember names
#define AXIS_LY Ch3		// Left up-down axis
#define AXIS_RY Ch2		// Right up-down axis
#define AXIS_LX Ch4		// Left right-left axis
#define AXIS_RX Ch1		// Right right-left axis

// Defines the toggle buttons
#define BTN_DTOGGLE Btn6U	// Button for toggling the drive mode
#define BTN_REVERSE Btn5U	// Button for toggling the reversed mode

// Defines the action buttons
#define BTN_NET_UP Btn7U	// Button to bring the net up
#define BTN_NET_DOWN Btn7D	// Button to bring the net down
#define BTN_SWEEPER Btn8D	// Button to move the sweeper in the positive direction
#define BTN_SWEEPER_REVERSE Btn8U	// Button to move the sweper in the negative direction
#define BTN_WATER Btn8R		// Button to move the continuous water servo in the positive direction
#define BTN_WATER_REVERSE Btn8L	// Button to move the continuous water servo in the negative direction

#define WATER_SERVO_TIME 4 	// Time for the servo to complete one full range of its motion
#define WATER_SERVO_SPEED (255 / WATER_SERVO_TIME) // The speed of the servo, converted to servo units per second (assuming 255 possible servo units)

// Global bools to track the status of the robot
bool tankDrive = true;		// Tracks the status of the current drive mode
bool reversed = false;		// Tracks whether the robot is reversed or not
#endif
