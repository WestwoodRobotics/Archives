#ifndef RCONFIG
#define RCONFIG

#define DEADBAND_LIMIT 10.0
#define DEADBAND(x) ((x >= DEADBAND_LIMIT) ? x : 0.0)

#endif
