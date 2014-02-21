import kivy

kivy.require('1.7.2')

from kivy.app import App
from kivy.clock import Clock
from kivy.metrics import Metrics
from kivy.properties import NumericProperty
from kivy.properties import ObjectProperty
from kivy.properties import StringProperty
from kivy.uix.anchorlayout import AnchorLayout
from kivy.uix.boxlayout import BoxLayout
from kivy.uix.relativelayout import RelativeLayout
from kivy.uix.button import Button
from kivy.uix.floatlayout import FloatLayout
from kivy.uix.label import Label
from kivy.uix.popup import Popup
from kivy.uix.scatter import Scatter
from kivy.uix.treeview import TreeView, TreeViewLabel
from kivy.uix.widget import Widget
from kivy.config import Config
from nt_client import NetworkTableClient

Config.set('graphics', 'width', '1100')
Config.set('graphics', 'height', '500')
Config.set('graphics', 'resizable', '0')

robot = True

class Dashboard(FloatLayout):
    
    batteryValue = NumericProperty(50)
    
    get_items = list()
    
    set_items = list()
    
    def __init__(self, nt = None):
        super(Dashboard, self).__init__()

        self.nt = nt


    def add_get_item(self, _id, value, default):
        self.nt.watch(value, _id, self.__update_value)
        self.ids[_id].value = self.nt.getValue(value, default)
        print("Added Get Item '{0}' reading value '{1}' with default value {2}".format(_id, value, default))
    
    def add_set_item(self, _id, value):
        self.set_items.append([_id, value])
        print("Added Set Item '{0}' providing value '{1}'".format(_id, value))
    
    def do_action(self):
        print("Test")
    
    def change_drivemode(self, mode):
        self.nt.setValue("/Robot/Drivetrain/drivemode", mode * 1.0)
    
    def __update_value(self, _id, value):
        self.ids[_id].value = value


class DashboardApp(App):
    def build(self):
        return Dashboard(NetworkTableClient("2583", True) if robot else None)

if __name__ == '__main__':
    DashboardApp().run()

# import kivy

# kivy.require('1.7.2')

# from kivy.app import App
# from kivy.clock import Clock
# from kivy.metrics import Metrics
# from kivy.properties import NumericProperty
# from kivy.properties import ObjectProperty
# from kivy.properties import StringProperty
# from kivy.properties import Property
# from kivy.uix.anchorlayout import AnchorLayout
# from kivy.uix.boxlayout import BoxLayout
# from kivy.uix.relativelayout import RelativeLayout
# from kivy.uix.button import Button
# from kivy.uix.floatlayout import FloatLayout
# from kivy.uix.label import Label
# from kivy.uix.popup import Popup
# from kivy.uix.scatter import Scatter
# from kivy.uix.treeview import TreeView, TreeViewLabel
# from kivy.uix.widget import Widget
# from kivy.config import Config
# from nt_client import NetworkTableClient

# Config.set('graphics', 'width', '1100')
# Config.set('graphics', 'height', '500')
# Config.set('graphics', 'resizable', '0')

# robot = True

# class Dashboard(FloatLayout):
#     batteryValue = NumericProperty(50)

#     get_items = list()

#     get_values = dict()

#     set_items = list()

#     def __init__(self, nt = None):
#         super(Dashboard, self).__init__()
        
#         if robot:
#             Clock.schedule_interval(self.__update_values, 0.1)
        
#         self.__nt_setup(self)
        
#         if robot:
#             nt.setValue("/computer/battery_level", 0.0)
#             nt.setValue("/computer/drive_mode_text", "Arcade")
        
#         self.nt = nt
    
#     def __nt_setup(self, array):
#         for a in array.children:
#             if a.children:
#                 self.__nt_setup(a)
#             else:
#                 if hasattr(a, 'nt_action'):
#                     if a.nt_action == "get":
#                         self.add_get_item(a)

#     def add_get_item(self, _id):
#         self.get_values[_id.nt_key] = Property(_id.nt_default)
#         _id.value = self.get_values[_id.nt_key]
#         self.get_items.append(_id.nt_key)
#         print("Added Get Item '{0}".format(_id.nt_key))

#     def add_set_item(self, id, value):
#         self.set_items.append(value)
#         print("Added Set Item '{0}' providing value '{1}'".format(_id, value))

#     def do_action(self):
#         print("Test")

#     def change_drive_mode(self, mode):
#         print("Dummy Action - change_drive_mode - mode={0}".format(mode))

#     def __update_values(self, nope):
#         for g in self.get_items:
#             if robot:
#                 self.get_values[g] = self.nt.getValue("/computer/" + g)
        

# class DashboardApp(App):
#     def build(self):
#         return Dashboard(NetworkTableClient("2583", True) if robot else None)

# def printAll(array):
#     for a in array.children:
#         if a.children:
#             printAll(a)
#         else:
#             print(dir(a))


# if __name__ == '__main__':
#     dash = DashboardApp()
#     dash.run()
