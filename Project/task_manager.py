from datetime import date
import os.path

correct_credentials = False

current_user = " "

tasks = open("tasks.txt", "r")
users = open("user.txt", "r")

# slices username and password out of the first line and adda it to a list of correct passwords and usernames
correct_username_list = []                                                                 
correct_password_list = []
for line in users:
    line = line.strip()
    list1 = line.split(", ", )
    correct_username_list.append(list1[0])
    correct_password_list.append(list1[1])
    
# while loop keeps asking user for username and password till username and password is correct
# if the inputted username and password matches the username and password in txt file user.txt then correct_credentials changes to true ending the while loop
# determines the current user of program
while correct_credentials == False:
    username = input("Please enter your username:\n")
    password = input("Please enter your password:\n")

    if username in correct_username_list and password in correct_password_list:
        correct_credentials = True
        current_user = username 
    else:
        print("\nThe entered username or password is not correct, please try again\n")

tasks.close()
users.close()

# ----------definimng function reg_user----------

def reg_user(user_choice):
    if user_choice == "r":
        users = open("user.txt", "r+")
        
        # creates a list of usernames that are already in the txt file users.txt  
        username_list = []
        for line in users:                     
            list1 = line.split(", ",)
            username_list.append(list1[0])

        user_in_file = True
        while user_in_file == True:
            new_username = input("Please enter a new username that you want to register:\n")
            new_password = input("Please enter a new password that you want to use with this new username:\n")
            
            # checks if the username is in the username list
            if new_username in username_list:
                print("This username is already registerd")
            else:
                user_in_file = False
        # requests that user re enters password to confirm it 
        correct_password = False   
        while correct_password == False:
            password_check = input("Please re-enter the new password to confirm it:\n")

            # if the re enterd password does not match the while loop repeats and requests that user re enters the password again
            if password_check == new_password:
                correct_password = True
            else:
                print("The password enterd does not match, please try again")

        users.write("\n")
        users.write(new_username + "," + " " + new_password)

        users.close()

# ----------defining function add_task----------

def add_task(user_choice):
    if user_choice == "a":
        
        
        tasks = open("tasks.txt","a+")
        
        today = date.today()
        assigned_date = today.strftime("%d %b %Y")

       # gathers information of the new task 
        user = input("Which username is this task assigned to?:\n") 
        task_name = input("What is the name of this task?:\n")
        task_discription = input("What should the user do in this task?:\n")
        due_date = input("By what date should this task be complete?:(20 Dec 2021)\n")

        tasks.write("\n" + user + "," + " " + task_name + "," + " " + task_discription + "," + " " + assigned_date + "," + " " + due_date + "," + " " + "No")    # writing task in the specific format in text file tasks.txt

        tasks.close()

# ----------defining function view_all----------

def view_all(user_choice):
    if user_choice == "va":
        tasks = open("tasks.txt", "r")
         
        # repeating the prosses for every line in text file tasks.txt
        # creating list for each line by spliting each part of task seperated by a comma
        for line in tasks:
            line = line.strip()
            list1 = line.split(", ", )
            user = list1[0]                                                              
            task_name = list1[1]
            task_description = list1[2]
            assigned_date = list1[3]
            due_date = list1[4]
            task_status = list1[5]

            print(f"\nTask:{task_name} \nAssigned to:{user} \nDate assigned:{assigned_date} \nDue date:{due_date} \nTask complete?{task_status}\nTask description:\n{task_description}")
        
        tasks.close()

# ----------defining function view_my----------
def view_my(user_choice):
    if user_choice == "vm":
        tasks = open("tasks.txt", "r")

        # creating list for each line by spliting each part of task seperated by a comma
        # slicing the first part of the list which is the username of the person doing the task
        task_num = 1
        for line in tasks:
            list1 = line.split(", ", )                                       
            user = list1[0]
            task_name = list1[1]
            task_description = list1[2]
            assigned_date = list1[3]
            due_date = list1[4]
            task_status = list1[5]

            # if the current user which is determined in the start of the code is = to the first word in the line then it prints it out 
            if user == current_user: 
                print(f"\n{task_num}:Task:{task_name} \nAssigned to:{user} \nDate assigned:{assigned_date} \nDue date:{due_date} \nTask complete?{task_status}Task description:\n{task_description}")
                task_num += 1
            
        tasks.close()

        tasks = open("tasks.txt", "r")
        lines = tasks.readlines()

        # asks the user which task they want to view 
        task_view = int(input("\nWhich task do you want to view:\n")) -1  

        # selecting the line if the task which the user has chosen
        selected_line = lines[task_view]
        
        # creating a list of the char in the line 
        selected_line = selected_line.strip()
        list1 = selected_line.split(", ", )
        user = list1[0]                                                               
        task_name = list1[1]
        task_description = list1[2]
        assigned_date = list1[3]
        due_date = list1[4]
        task_status = list1[5]


        print("Do you want to:\n1 - edit this task\n2 - mark this task as complete\n-1 - go back to main menu")

        edit_or_mark = str(input("Please enter the number here:\n"))
        
        # if the user wants to edit the task the following criteria is prompted and chnged in text file tasks.txt 
        if edit_or_mark == "1": 
            if task_status == "No":
                edited_username = input("What is the edited username?:\n")
                edited_due_date = input("What is the edited due date?(20 Dec 2021):\n")
                
                lines[task_view] = (f"{edited_username}, {task_name}, {task_description}, {assigned_date}, {edited_due_date}, {task_status}\n")

            # if task is already complete it cant be edited and this message is displayed
            else:
                print("This task has already been completed and can't be edited")

        # if the user wants to mark task as complete the last char which it the task status gets changed to yes 
        elif edit_or_mark == "2": 
            lines[task_view] = (f"{user}, {task_name}, {task_description}, {assigned_date}, {due_date}, Yes\n")

        # if user selects return to main menu it returs them to the choices they had     
        elif edit_or_mark == "-1":
            print("Please select one of the following options:\nr - register a user\na - add a task\nva - view all tasks\nvm - view my tasks\ngr - generate reports\nds - display statisticks\ne - exit")

            user_choice = input("Please enter your choice here:")
        
            reg_user(user_choice)
            add_task(user_choice)
            view_all(user_choice)
            view_my(user_choice)    
            generate_report(user_choice)
            display_statistics(user_choice)
            exit_project(user_choice)
    
    
    

        tasks = open("tasks.txt", "w")
        tasks.writelines(lines)
        tasks.close()
       
# ----------defining function generate_report----------
def generate_report(user_choice):
    if user_choice == "gr":
        task_overview = open("task_overview.txt", "w")
        tasks = open("tasks.txt", "r")
        users = open("user.txt", "r")

        today = date.today()
        today = today.strftime("%d %b %Y")

        total_tasks = 0
        complete_tasks = 0
        uncomplete_tasks = 0
        overdue_and_uncomplete = 0
        
       # each loop the task number gos up by one to generat the total number of tasks in file
       # for each line in file tasks.txt
       # creats a list for each line by spliting each part of task seperated by a comma
       # slicing parts of the line to gather criteria of each task
        for line in tasks:
            total_tasks += 1

            line = line.strip()
            list1 = line.split(", ", )                                        
            user = list1[0] 
            task_name = list1[1]
            task_description = list1[2]
            assigned_date = list1[3]
            due_date = list1[4]
            task_status = list1[5]
            

            # gathering information about task for example if the task is complet or uncomplete
            if task_status == "Yes": 
                complete_tasks += 1
            else:
                uncomplete_tasks += 1

            # cant devide by 0
            if uncomplete_tasks == 0: 
                precentage_uncomplete = 0
            else:
                precentage_uncomplete = round((total_tasks / uncomplete_tasks) * 100, 2) 

            if overdue_and_uncomplete == 0:
                precentage_overdue = 0
            else:
                precentage_overdue = round((total_tasks / overdue_and_uncomplete) * 100, 2)

        # the total tasks in the txt file tasks.txt gets subtracted by 2 because there was already 2 tasks in file by default meaning the rest was added using  this program
        num_of_tasks = total_tasks - 2
        
        task_overview.write(f"The number of tasks added:{num_of_tasks}\nThe number of complete tasks:{complete_tasks}\nThe number of uncomplete tasks:{uncomplete_tasks}\nThe number of uncomplete and overdue:{overdue_and_uncomplete}\nThe prcentage of uncomplete tasks:{precentage_uncomplete}\nThe precentage overdue:{precentage_overdue}")

        task_overview.close()
        tasks.close()
        users.close()


        user_overview = open("user_overview.txt", "w")
        users = open("user.txt", "r")
        tasks = open("tasks.txt", "r")
        users_count = 0

        # for each user in txt file user.txt
        for line in users:
            user_tasks = 0
            complete_tasks = 0
            uncomplete_tasks = 0
            users_count += 1

           # determins the user of each loop 
            list1 = line.split(", ",)
            selected_user = list1[0]

            # creating list for each line by spliting each part of task seperated by a comma
            for line in tasks:
                line = line.strip()
                list1 = line.split(", ", )                                        
                user = list1[0]                                                         
                task_name = list1[1]
                task_description = list1[2]
                assigned_date = list1[3]
                due_date = list1[4]
                task_status = list1[5]

                # if the selected user of the loop is found in a line of txt file tasks 1 is added tovariable user_task the keep count of how many tasks they have
                if user == selected_user:
                    user_tasks += 1

                # if the task (line) starts with the selected user and the status of that task is yes 1 gets added to variiable complete_tasks to keep count of their complete tasks
                if user == selected_user and task_status == "Yes":
                    complete_tasks += 1

                if  user == selected_user and task_status == "No":
                    uncomplete_tasks += 1

            # cant devide by 0
            # calculates precentage complete 
            if complete_tasks == 0:
                precentage_complete = 0
            else:
                precentage_complete = (user_tasks / complete_tasks) * 100

            if uncomplete_tasks == 0:
                precentage_uncomplete = 0
            else:
                recentage_uncomplete = (user_tasks / uncomplete_tasks) * 100 
                    
            if user_tasks == 0:
                user_task_precentage = 0
            else:
                user_task_precentage = (total_tasks / user_tasks) * 100

            # there was already 1 task by default so the rest was added using this program        
            num_of_users = users_count - 1

            user_overview.write(f"\nData for user:{selected_user}\nTotal tasks assigned to {selected_user}:{user_tasks}\nThe precentage of tasks assigned to {selected_user}:{user_task_precentage}\nCompleted task precentage:{precentage_complete}\nUncomplete task precentage:{precentage_uncomplete}\n")  

        user_overview.write(f"\nThe total number of users registerd using this program:{num_of_users}\nThe total tasks created using this program:{num_of_tasks}\n")

        
        tasks.close()
        users.close()
        user_overview.close()

# ----------defining function display_statistics----------
def display_statistics(user_choice):
    if user_choice == "ds":

        # checks if the files exists
        if os.path.isfile("task_overview.txt") == True and os.path.isfile("user_overview.txt") == True: 
            task_overview = open("task_overview.txt", "r")
            user_overview = open("user_overview.txt", "r")
            for line in task_overview:
                print(line) 

            for line in user_overview:
                print(line)
        else:                                                                                            
            user_choice = "gr"
            generate_report(user_choice)
            print("Generating report")

# ----------defining function exit_project----------

# if user enters e it exits the program
def exit_project(user_choice):
    if user_choice == "e":                        
        quit()




print("Please select one of the following options:\nr - register a user\na - add a task\nva - view all tasks\nvm - view my tasks\ngr - generate reports\nds - display statisticks\ne - exit")

user_choice = input("Please enter your choice here:")

# calling all functions        
reg_user(user_choice)                                                                
add_task(user_choice)
view_all(user_choice)
view_my(user_choice)    
generate_report(user_choice)
display_statistics(user_choice)
exit_project(user_choice)
    
    
    
    



