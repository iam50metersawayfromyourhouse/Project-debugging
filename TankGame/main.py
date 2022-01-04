import random # to randomize enemy position

size = 7


class Panzer:

    def __init__(self, direction_facing, position_x, position_y, position_of_enemy_x, position_of_enemy_y):
        # initializing vars
        self.direction = direction_facing
        self.position_x = position_x
        self.position_y = position_y
        self.shell_position_x = position_x
        self.shell_position_y = position_y
        self.position_of_enemy_x = position_of_enemy_x
        self.position_of_enemy_y = position_of_enemy_y
        self.game_over = False
        # self.direction_options = ['left', 'right', 'up', 'down']

    def move(self): # moves player tank and also the enemy tank
        new_position_x, new_position_y = input('\nWhere are we moving commander? [col|row]: ').split()
        self.position_x = int(new_position_x)
        self.position_y = int(new_position_y)
        enemy_not_in_player = False
        while not enemy_not_in_player:
            rand_x = random.randint(0, 6)
            rand_y = random.randint(0, 6)
            if rand_x == self.position_x and rand_y == self.position_y:
                pass
            else:
                self.position_of_enemy_x = rand_x
                self.position_of_enemy_y = rand_y
                enemy_not_in_player = True

    def facing(self): # changes the direction the tank is facing
        new_direction = input("\nWhich direction should we traverse the gun? [left|right|up|down|n]: ")
        if new_direction != 'n':
            self.direction = new_direction
        else:
            self.direction = self.direction
            print("\nRoger that, staying still!")

    def fire(self):
        request = input("\nShould we fire commander? [y|n]: ")
        if request.casefold() == 'y':
            print("Firing!")
            if self.direction == 'left': # fires in a straight line and if statement confirms if the shell hit the enemy
                for i in range(size):
                    self.shell_position_x -= 1
                    if self.shell_position_x == self.position_of_enemy_x and self.shell_position_y == self.position_of_enemy_y:
                        print("Nailed it!")
                        print("Game over")
                        self.game_over = True
            elif self.direction == 'right':
                for i in range(size):
                    self.shell_position_x += 1
                    if self.shell_position_x == self.position_of_enemy_x and self.shell_position_y == self.position_of_enemy_y:
                        print("Nailed it!")
                        print("Game over")
                        self.game_over = True
            elif self.direction == 'up':
                for i in range(size):
                    self.shell_position_y -= 1
                    if self.shell_position_x == self.position_of_enemy_x and self.shell_position_y == self.position_of_enemy_y:
                        print("Nailed it!")
                        print("Game over")  
                        self.game_over = True
            elif self.direction == 'down':
                for i in range(size):
                    self.shell_position_y += 1
                    if self.shell_position_x == self.position_of_enemy_x and self.shell_position_y == self.position_of_enemy_y:
                        print("Nailed it!")
                        print("Game over")
                        self.game_over = True
            else:
                print("It's not quite right yet, we missed!")
        elif request.casefold() == 'n':
            print("\nRoger that holding fire!")
        else:
            print(f"We don't understand!")


# variables
grid = [['0', '0', '0', '0', '0', '0', '0'],
        ['0', '0', '0', '0', '0', '0', '0'],
        ['0', '0', '0', '0', '0', '0', '0'],
        ['0', '0', '0', '0', '0', '0', '0'],
        ['0', '0', '0', '0', '0', '0', '0'],
        ['0', '0', '0', '0', '0', '0', '0'],
        ['0', '0', '0', '0', '0', '0', '0']]
tank_phase = {"left": "-DMM", # player and enemy "phases" (direction where player is looking)
              "right": "MMD-",
              "up": "q|p",
              "down": "d|b",
              "amogus": "-DHH", # enemy tank visualised (funny moment)
              "empty": '0',}

tank = Panzer('up', 6, 6, 4, 4)
game_is_still_running = tank.game_over

# draws the tanks in the grid

def draw_tank(phase):
    grid[tank.position_y][tank.position_x] = tank_phase[phase]


def draw_enemy(phase):
    grid[tank.position_of_enemy_y][tank.position_of_enemy_x] = tank_phase[phase]

# real game ->


while not game_is_still_running:
    draw_enemy("amogus")
    draw_tank(tank.direction)
    print("\n", *grid, sep='\n')
    draw_tank("empty")
    draw_enemy("empty")
    tank.move()
    tank.shell_position_x = tank.position_x
    tank.shell_position_y = tank.position_y
    draw_tank(tank.direction)
    draw_enemy("amogus")
    print(*grid, sep='\n') # prints the grid
    tank.facing()
    draw_tank(tank.direction)
    print(*grid, sep='\n')
    tank.fire()
    print(tank.shell_position_x, tank.shell_position_y)
    print(tank.position_of_enemy_x, tank.position_of_enemy_y)
    game_is_still_running = tank.game_over
