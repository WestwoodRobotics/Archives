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

Config.set('graphics', 'width', '600')
Config.set('graphics', 'height', '400')
Config.set('graphics', 'resizable', '0')

NetworkTable.SetIPAddress("192.168.1.104")
NetworkTable.SetClientMode()
NetworkTable.Initialize()
RoboTable = NetworkTable.GetTable("Robot")
RoboDrive = RoboTable.GetSubTable("Drivetrain")
RoboCatcher = RoboTable.GetSubTable("Catcher")
RoboSensors = RoboTable.GetTable("Sensors")

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

    def change_spindle_scale(self, speed):
        a = float(speed)
        if a > 10.0:
            a = 10.0
            self.ids['spindle_scaler'].text = str(a)
        if a < 0.0:
            a = 0.0
            self.ids['spindle_scaler'].text = str(a)
        RoboCatcher.PutNumber("spindleScale", a/10)

    def change_move_scale(self, speed):
        a = float(speed)
        if a > 10.0:
            a = 10.0
            self.ids['move_scaler'].text = str(a)
        if a < 0.0:
            a = 0.0
            self.ids['move_scaler'].text = str(a)
        RoboDrive.PutNumber("move_scale", a/10)
    
    def __update_value(self, _id, value):
        self.ids[_id].value = value


class DashboardApp(App):
    def build(self):
        global Dash
        Dash = Dashboard()
        return Dash

def dashChanger(_id, value):
    Dash.ids[_id].value = value

def dashTextChanger(_id, value):
    Dash.ids[_id].text = str(value)

def modeChanger(mode):
    {
        1: Dash.ids['toggle_drivemode_arcade'],
        2: Dash.ids['toggle_drivemode_tank'],
        3: Dash.ids['toggle_drivemode_mecanum'],
        4: Dash.ids['toggle_drivemode_mecatank']
    }[mode]._do_press()

class RobotTableListener(ITableListener):
    def ValueChanged(self, table, key, value, isNew):
        if key == "battery_level":
            dashChanger('battery_level_bar', table.GetValue(key))
roboListener = RobotTableListener()
RoboTable.AddTableListener(roboListener)

class DriveTableListener(ITableListener):
    def ValueChanged(self, table, key, value, isNew):
        if key == "drivemode_string":
            dashTextChanger('text_drivemode', table.GetValue(key))
        elif key == "drivemode":
            modeChanger(table.GetValue(key))
        elif key == "move_scale":
            dashTextChanger('move_scaler', table.GetValue(key)*10)

class CatcherTableListener(ITableListener):
    def ValueChanged(self, table, key, value, isNew):
        if key == "spindleScale":
            dashTextChanger('spindle_scaler', table.GetValue(key)*10)

class SensorTableListener(ITableListener):
    def ValueChanged(self, table, key, value, isNew):
        if key == "distance":
            dashTextChanger("ultrasonic_out", table.GetValue(key))


driveListener = DriveTableListener()

RoboDrive.AddTableListener(driveListener)


if __name__ == '__main__':
    DashboardApp().run()