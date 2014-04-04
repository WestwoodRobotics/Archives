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
from pynetworktables import *

Config.set('graphics', 'width', '750')
Config.set('graphics', 'height', '500')
Config.set('graphics', 'resizable', '0')

NetworkTable.SetIPAddress("192.168.1.104")
NetworkTable.SetClientMode()
NetworkTable.Initialize()
RoboTable = NetworkTable.GetTable("Robot")
RoboDrive = RoboTable.GetTable("Drivetrain")

Dash = None

class Dashboard(FloatLayout):
    
    batteryValue = NumericProperty(50)
    
    get_items = list()
    
    watch = list()

    set_items = list()
    
    def __init__(self):
        super(Dashboard, self).__init__()

    def add_get_item(self, _id, value, default):
        self.watch.append([_id, self.__update_value])
        #self.ids[_id].value = self.nt(value, default)
        print("Added Get Item '{0}' reading value '{1}' with default value {2}".format(_id, value, default))
    
    def add_set_item(self, _id, value):
        self.set_items.append([_id, value])
        print("Added Set Item '{0}' providing value '{1}'".format(_id, value))
    
    def do_action(self):
        print("Test")
    
    def change_drivemode(self, mode):
        RoboDrive.PutNumber("drivemode", mode * 1.0)
    
    def __update_value(self, _id, value):
        self.ids[_id].value = value


class DashboardApp(App):
    def build(self):
        global Dash
        Dash = Dashboard()
        return Dash

def dashChanger(_id, value):
    Dash.ids[_id].value = value

class DriveTableListener(ITableListener):
    def ValueChanged(self, table, key, value, isNew):
        if key == "drivemode_string":
            dashChanger('text_drivemode', table.GetValue(key))

driveListener = DriveTableListener()

RoboDrive.AddTableListener(driveListener)


if __name__ == '__main__':
    DashboardApp().run()