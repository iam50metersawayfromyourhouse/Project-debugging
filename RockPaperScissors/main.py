import random

a_draw = False


def main_():
    global a_draw
    game_end = False
    while not game_end:
        computer_choices = ["rock", "paper", "scissors"]
        player_dec = input("Welcome to rock, paper, and scissors!\n"
                           "You will be fighting a computer that's randomized good luck!\n"
                           "Enter your input [rock|paper|scissors] : ")
        computer_decision = computer_choices[random.randint(0, 2)]
        losing_moves = {"rock": 'paper',
                        "paper": 'scissors',
                        "scissors": "rock"}
        winning_moves = {"rock": "scissors",
                         "paper": "rock",
                         "scissors": "paper"}
        computer_string = f"Computer chose {computer_decision}"
        print(computer_string)
        for i in losing_moves:
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
