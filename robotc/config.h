#ifndef RCONFIG
#define RCONFIG

// Deadband keeps small signals from being sent from the joysticks
#define DEADBAND_LIM 10.0
#define DEADBAND(x) (abs(x) > DEADBAND_LIM ? x : 0)

// Defines the axes with easier-to-remember names
#define AXIS_LY Ch3
#define AXIS_RY Ch2
#define AXIS_LX Ch4
#define AXIS_RX Ch1

// Defines the toggle buttons
#define BTN_DTOGGLE Btn6U
#define BTN_REVERSE Btn5U

// Defines the action buttons
#define BTN_NET_UP Btn7U
#define BTN_NET_DOWN Btn7D
#define BTN_SWEEPER Btn8D

// Global bools to track the status of the robot
bool tankDrive = true;
bool reversed = false;

#endif
