#ifndef RCONFIG
#define RCONFIG

#define DEADBAND_LIM 10.0
#define DEADBAND(x) (abs(x) > DEADBAND_LIM ? x : 0)

#define AXIS_LY Ch3
#define AXIS_RY Ch2
#define AXIS_LX Ch4
#define AXIS_RX Ch1

#define BTN_DTOGGLE Btn6U
#define BTN_REVERSE Btn5U

bool tankDrive = true;
bool reversed = false;

#endif
