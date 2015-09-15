coal_val = 5
coal_points = 0
magnetite_val = 7
magnetite_points = 0
bauxite_val = 10
bauxite_points = 0
chalcopyrite_val = 15
chalcopyrite_points = 0
spodumene_val = 25
spodumene_points = 0
core_sample_base_val = 50 
core_sample_points = 0
replacement_val = 100
rep_pipe_points = 0
rep_filter_points = 0
limestone_val = 2
limestone_points = 0

temp = int(raw_input("Please enter the core sample returned: none(0), small(1), medium(2), or large(3): "))
if (temp < 4):
	core_sample_points = core_sample_base_val * temp

temp = int(raw_input("Did you get a Replacement Air Filter? yes(1), no(0): "))
if (temp < 2):
	rep_filter_points = replacement_val * temp

temp = int(raw_input("Did you get a Replacement Pipe? yes(1), no(0): "))
if (temp < 2):
        rep_pipe_points = replacement_val * temp

temp = int(raw_input("How many coal: "))
if (temp < 25):
	coal_points = temp * coal_val

temp = int(raw_input("How many magnetite: "))
if (temp < 21):
        magnetite_points = temp * magnetite_val

temp = int(raw_input("How many bauxite: "))
if (temp < 17):
        bauxite_points = temp * bauxite_val

temp = int(raw_input("How many chalcopyrite: "))
if (temp < 13):
        chalcopyrite_points = temp * chalcopyrite_val

temp = int(raw_input("How many spodumene: "))
if (temp < 9):
        spodumene_points = temp * spodumene_val

temp = int(raw_input("How much limestone aggregate: "))
if (temp < 6):
        limestone_points = temp * limestone_val

print
print
print

total_points = coal_points + magnetite_points + bauxite_points + chalcopyrite_points + spodumene_points + limestone_points + rep_filter_points + rep_pipe_points + core_sample_points

print "Total points: " + str(total_points)

print
