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

#define BTN_NET_UP Btn7U
#define BTN_NET_DOWN Btn7D
#define BTN_SLIDER_UP Btn8U
#define BTN_SLIDER_DOWN Btn8D

bool tankDrive = true;
bool reversed = false;

#endif
