extends KinematicBody2D

# GLOBAL VARIABLES
export var acceleration = 1000
export var max_speed = 10000
export var friction = 1000

var velocity = Vector2.ZERO

# PLAYER MOVEMENT
func _physics_process(delta):

	# GET VELOCITY
	var input_vector = Vector2.ZERO
	input_vector.x = Input.get_action_strength("ui_right") - Input.get_action_strength("ui_left")
	input_vector.y = Input.get_action_strength("ui_down") - Input.get_action_strength("ui_up")
	input_vector = input_vector.normalized()
	
	if input_vector != Vector2.ZERO:
		velocity += input_vector * acceleration * delta
		velocity = velocity.clamped(max_speed * delta)
	else:
		velocity = velocity.move_toward(Vector2.ZERO, friction * delta)
	
	# ACTION
	move_and_slide(velocity)
