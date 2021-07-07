import math

user_investment = ("investment", "Investment", "INVESTMENT")
user_bond = ("bond", "Bond", "BOND")

print("Choose either 'investment' or 'bond' from the menu below to proceed:"
      +"\ninvestment    - to calculate the amount of interest you'll earn on investment"
      +"\nbond          - to calculate the amount you'll have to pay a home loan \n")

user_choice = input("Enter your choice here:")

if user_choice in user_investment:
    user_deposit_amount = int(input("What is the amount you are depositing?:"))                                                            # collecting information from user needed to calculate interest on the users investment 
    interest_rate =int(input("What is the interest rate of your investment? (Just the number e.g 8 not 8%):"))/100     
    period_of_investment = int(input("How long are you planning to invest? (Years):"))
    interest = input("Do you want 'simple' or 'compound' interest:")
    if interest == "simple":
        future_amount = round(user_deposit_amount *(1 + interest_rate * period_of_investment), 2)                                          # calculating simple interest on users investment if they chose to do so 
        print(f"The amount you will have is {future_amount}")
    else:
        future_amount = round(user_deposit_amount * math.pow((1 + interest_rate),period_of_investment), 2)                                 # calculating compound interest on users investment if they chose to do so                                   
        print(f"The amount you will have is {future_amount}") 
else:
   if user_choice in user_bond:
       present_value = int(input("What is the present value of the house?:"))                                                              # collecting information to determine monthly payback amount
       monthly_interest_rate = int(input("What is the interest rate? (Just number e.g 8 not 8%):"))/12
       period_of_return = int(input("How long do plan to repay the bond? (Months):"))

       monthly_payment = round((monthly_interest_rate * present_value)/ math.pow(1 - (1 + monthly_interest_rate),- period_of_return), 2)   # calculating monthly payment amount using bond formula 
       print(f"You will need to pay {monthly_payment} per month")
   else:
       print("You have not entered any of the options correctly, please try again")
