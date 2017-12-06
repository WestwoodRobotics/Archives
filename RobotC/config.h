#ifndef CONFIG
#define CONFIG

//define numbers under here
#define maxShootAngle 127
#define minShootAngle -127

#define grabBtn Btn8D
#define releaseBtn Btn8U

#define leftXAxis Ch4
#define leftYAxis Ch3
#define rightYAxis Ch2

#define btnToggle Btn7U
#define slowToggle Btn7D
#define revToggle Btn7R

#define shootBtn Btn7L
#define shootBtn2 Btn8R

#define magRBtn Btn6U
#define magRDnBtn Btn6D
#define magLBtn Btn5U
#define magLDnBtn Btn5D

#define slowSpeed 0.5
bool isSlow = false;

bool isRev = false;

bool driveTypeArcade = false;	//Toggle button state

#endif
