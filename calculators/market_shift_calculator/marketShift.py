coal_expanded_percent = 0.0
coal_ore_percent = 0.0
coal_current_val = 5
coal_val = 0.0
coal_max_num = 24.0

magnetite_expanded_percent = 0.0
magnetite_ore_percent = 0.0
magnetite_current_val = 7
magnetite_val = 0.0
magnetite_max_num = 20.0

bauxite_expanded_percent = 0.0
bauxite_ore_percent = 0.0
bauxite_current_val = 10
bauxite_val = 0.0
bauxite_max_num = 16.0

chalcopyrite_expanded_percent = 0.0
chalcopyrite_ore_percent = 0.0
chalcopyrite_current_val = 15
chalcopyrite_val = 0.0
chalcopyrite_max_num = 12.0

spodumene_expanded_percent = 0.0
spodumene_ore_percent = 0.0
spodumene_current_val = 25
spodumene_val = 0.0
spodumene_max_num = 8.0

avg_expanded_percent = 0.0
average_percent = 0.0
min_percent = 0.0
max_percent = 0.0

print
match_num = int(input("How many matches: "))

temp = int(input("How many coal scored: "))
if (temp <= (coal_max_num * match_num)):
	coal_ore_percent = (float(temp) / (coal_max_num * match_num))
	min_percent = coal_ore_percent
	max_percent = coal_ore_percent

temp = (int)(input("How many magnetite scored: "))
if (temp <= (magnetite_max_num * match_num)):
    magnetite_ore_percent = (float(temp) / (magnetite_max_num * match_num))
if(min_percent > magnetite_ore_percent):
	min_percent = magnetite_ore_percent
if(max_percent < magnetite_ore_percent):
	max_percent = magnetite_ore_percent

temp = (int)(input("How many bauxite scored: "))
if (temp <= (bauxite_max_num * match_num)):
    bauxite_ore_percent = (float(temp) / (bauxite_max_num * match_num))
if(min_percent > bauxite_ore_percent):
	min_percent = bauxite_ore_percent
if(max_percent < bauxite_ore_percent):
	max_percent = bauxite_ore_percent

temp = (int)(input("How many chalcopyrite scored: "))
if (temp <= (chalcopyrite_max_num * match_num)):
    chalcopyrite_ore_percent = (float(temp) / (chalcopyrite_max_num * match_num))
if(min_percent > chalcopyrite_ore_percent):
	min_percent = chalcopyrite_ore_percent
if(max_percent < chalcopyrite_ore_percent):
	max_percent = chalcopyrite_ore_percent

temp = (int)(input("How many spodumene scored: "))
if (temp <= (spodumene_max_num * match_num)):
    spodumene_ore_percent = (float(temp) / (spodumene_max_num * match_num))
if(min_percent > spodumene_ore_percent):
	min_percent = spodumene_ore_percent
if(max_percent < spodumene_ore_percent):
	max_percent = spodumene_ore_percent

print

average_percent = (coal_ore_percent + magnetite_ore_percent + bauxite_ore_percent + chalcopyrite_ore_percent + spodumene_ore_percent) / 5
coal_expanded_percent = (coal_ore_percent - min_percent)/(max_percent - min_percent)
magnetite_expanded_percent = (magnetite_ore_percent - min_percent)/(max_percent - min_percent)
bauxite_expanded_percent = (bauxite_ore_percent - min_percent)/(max_percent - min_percent)
chalcopyrite_expanded_percent = (chalcopyrite_ore_percent - min_percent)/(max_percent - min_percent)
spodumene_expanded_percent = (spodumene_ore_percent - min_percent)/(max_percent - min_percent)

avg_expanded_percent = (average_percent - min_percent)/(max_percent - min_percent)

coal_shift =  avg_expanded_percent - coal_expanded_percent
magnetite_shift = avg_expanded_percent - magnetite_expanded_percent
bauxite_shift = avg_expanded_percent - bauxite_expanded_percent
chalcopyrite_shift = avg_expanded_percent - magnetite_expanded_percent
spodumene_shift = avg_expanded_percent - spodumene_expanded_percent

coal_val = coal_current_val + (coal_current_val * coal_shift)
magnetite_val = magnetite_current_val + (magnetite_current_val * magnetite_shift)
bauxite_val = bauxite_current_val + (bauxite_current_val * bauxite_shift)
chalcopyrite_val = chalcopyrite_current_val + (chalcopyrite_current_val * chalcopyrite_shift)
spodumene_val = spodumene_current_val + (spodumene_current_val * spodumene_shift)

print "Coal: " + str(coal_val) + " ( " + str(coal_shift * 100) + "% shift)"
print "Magnetite: " + str(magnetite_val) + " ( " + str(magnetite_shift * 100) + "% shift)"
print "Bauxite: " + str(bauxite_val) + " ( " + str(bauxite_shift * 100) + "% shift)"
print "Chalcopyrite: " + str(chalcopyrite_val) + " ( " + str(chalcopyrite_shift * 100) + "% shift)"
print "Spodumene: " + str(spodumene_val) + " ( " + str(spodumene_shift * 100) + "% shift)"
print
