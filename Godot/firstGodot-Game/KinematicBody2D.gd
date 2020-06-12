extends KinematicBody2D

# GLOBAL VARIABLES
var motion = Vector2() # direction and velocity
export var movement_speed = 250 # exports variable to inspector
var mov_x = movement_speed
var mov_y = movement_speed

# PLAYER MOVEMENT
func _physics_process(delta):
	
	# LEFT ARROW
	if Input.is_action_pressed("ui_left"):
		motion.x = -mov_x
	elif Input.is_action_pressed("ui_right"):
		motion.x = mov_x
	else:
		motion.x = 0
		
	# RIGHT ARROW
	if Input.is_action_pressed('ui_up'):
		motion.y = -mov_y
	elif Input.is_action_pressed('ui_down'):
		motion.y = mov_y
	else:
		motion.y = 0
	
	# ACTION
	move_and_slide(motion)
