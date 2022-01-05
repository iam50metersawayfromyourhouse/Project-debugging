import random


def main_():
    game_end = False
    while not game_end:
        computer_choices = ["rock", "paper", "scissors"] # set of choices (random) for the computer
        player_dec = input("Welcome to rock, paper, and scissors!\n"
                           "You will be fighting a computer that's randomized good luck!\n"
                           "Enter your input [rock|paper|scissors] : ")
        computer_decision = computer_choices[random.randint(0, 2)]
        losing_moves = {"rock": 'paper', # player moves that ends in a loss
                        "paper": 'scissors',
                        "scissors": "rock"}
        winning_moves = {"rock": "scissors", # player moves that ends with a victory
                         "paper": "rock",
                         "scissors": "paper"}
        computer_string = f"Computer chose {computer_decision}"
        print(computer_string)
        for i in losing_moves: # check if player won / los -->
            if player_dec == i and computer_decision == losing_moves[i]:
                losing_string = "You lost!"
                print(losing_string)
                game_end = True
                break
        for i in winning_moves:
            if player_dec == i and computer_decision == winning_moves[i]:
                winning_string = "You won!"
                print(winning_string)
                game_end = True
                break
        if player_dec == computer_decision:
            draw_string = "It's a draw!"
            print(draw_string)


main_()
