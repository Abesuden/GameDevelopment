extends KinematicBody2D

# GLOBAL VARIABLES
export var acceleration = 1200
export var max_speed = 400
export var friction = 2400
export var prioritize_X_axis = true
var vertical_priority = 1

var velocity = Vector2.ZERO

# ANIMATE PLAYER
onready var animationTree = $AnimationTree
onready var animationState = animationTree.get("parameters/playback")

# DETERMIN CONTROL AXIS PRIORITY
func _ready():
	if (prioritize_X_axis):
		vertical_priority = 0.9
	else:
		vertical_priority = 1.1
	animationTree.active = true

# PLAYER MOVEMENT AND ANIMATION
func _physics_process(delta):

	# GET VELOCITY
	var input_vector = Vector2.ZERO
	input_vector.x = Input.get_action_strength("ui_right") - Input.get_action_strength("ui_left")
	input_vector.y = (Input.get_action_strength("ui_down") - Input.get_action_strength("ui_up")) * vertical_priority
	input_vector = input_vector.normalized()
	
	if input_vector != Vector2.ZERO:
		animationTree.set("parameters/Idle/blend_position", input_vector)
		animationTree.set("parameters/Run/blend_position", input_vector)
		animationState.travel("Run")
		velocity = velocity.move_toward(input_vector * max_speed, acceleration * delta)
	else:
		animationState.travel("Idle")
		velocity = velocity.move_toward(Vector2.ZERO, friction * delta)
	
	# ACTION
	velocity = move_and_slide(velocity)
