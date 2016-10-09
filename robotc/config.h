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
#define BTN_SWEEPER_REVERSE Btn8U
#define BTN_WATER Btn8R
#define BTN_WATER_REVERSE Btn8L

#define WATER_SERVO_TIME 4 // Time for the servo to complete one full range of its motion
#define WATER_SERVO_SPEED (255 / WATER_SERVO_TIME) // The speed of the servo, converted to servo units per second (assuming 255 possible servo units)

// Global bools to track the status of the robot
bool tankDrive = true;
bool reversed = false;
#endif
