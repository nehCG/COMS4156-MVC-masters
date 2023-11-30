# End to End Testing Checklist
To ensure the system or application are functioning as expected.

URL: http://localhost:8080/ems/index

## Login Checklist 
1. Incorrect password
- [ ] Enter an existed Username (e.g. "admin")
- [ ] Enter incorrect password (e.g. "1")
- [ ] The system should show "Incorrect Password!"


2. Not exist user
- [ ] Enter a not existed Username (e.g. "a")
- [ ] Enter Password 
- [ ] The system should show "User does not exist!"


3. Login success
- [ ] Enter an existed Username "admin"
- [ ] Enter the correct Password "123456"
- [ ] The system should show "Login Successful！"
- [ ] The system will turn to the main page.


## Password Change Checklist
1. Change password successfully
- [ ] Go to USERNAME->Pwd. 
- [ ] Enter the correct old password
- [ ] Enter a different new password, and repeat the new password correctly.
- [ ] The system should show "Password changed successfully, exit in 3 seconds..." 
- [ ] A few seconds later, the system will turn to the login page.
- [ ] The user should be able to login with the new password.


2. Same old password and new password
- [ ] Go to USERNAME->Pwd.
- [ ] Enter the correct old password. 
- [ ] Enter the new passwords the same as the old password. 
- [ ] The system should show "New password cannot be same as old password!".


3. Incorrect original password
- [ ] Go to USERNAME->Pwd.
- [ ] Enter an incorrect old password. 
- [ ] Enter new passwords. 
- [ ] The system should show "Incorrect original password!".



## User Management Checklist
1. Search
- [ ] Go to User Mgmt
- [ ] Enter Username, Email, or Phone
- [ ] The users should be filtered accordingly


2. Add User with error
- [ ] Go to User Mgmt
- [ ] Click Add User
- [ ] Enter new user information but leave Email blank
- [ ] The system should show "Email format is incorrect"


3. Add User successfully
- [ ] Go to User Mgmt
- [ ] Click Add User
- [ ] Enter new user information
- [ ] The system should change to the User Mgmt page with the new user added
- [ ] Logout the current user
- [ ] Login with the new added user with Username and default password "123456"
- [ ] The new user should login successfully


4. Delete User
- [ ] Go to User Mgmt
- [ ] Click Delete next to a user
- [ ] Confirm delete
- [ ] The deleted user will be removed from the User Mgmt page


5. Edit User
- [ ] Go to User Mgmt
- [ ] Click Edit next to a user
- [ ] Change any field
- [ ] Click Edit next to the user just changed
- [ ] The change should be shown


## Role Management Checklist
1. Search Role
- [ ] Go to Role Mgmt
- [ ] Enter role name
- [ ] The roles should be filterd accordingly

2. Add Role with error
- [ ] Go to Role Mgmt
- [ ] Click Add role
- [ ] Enter new role information but leave Role Name blank
- [ ] The system should show "Required fields cannot be blank"


3. Add Role successfully
- [ ] Go to Role Mgmt
- [ ] Click Add role
- [ ] Enter new role information
- [ ] The system should change to the Role Mgmt page with the new role added
- [ ] Go to User Mgmt
- [ ] Click edit next to any user
- [ ] The added role should be shown in the role list


4. Delete Role
- [ ] Go to Role Mgmt
- [ ] Click Delete next to a role
- [ ] Confirm delete
- [ ] The deleted role will be removed from the Role Mgmt page


5. Edit Role
- [ ] Go to Role Mgmt
- [ ] Click Edit next to a role
- [ ] Change any field
- [ ] Click Edit next to the role just changed
- [ ] The change should be shown


6. Authorize Role errors
- [ ] Go to Role Mgmt
- [ ] Click Authorize without choosing any roles
- [ ] The system should show "Please select the role you want to authorize!"
- [ ] Click Authorize with more than one role selected
- [ ] The system should show "Multiple role authorize is not supported yet!"


7. Authorize Role Success
- [ ] Go to Role Mgmt
- [ ] Select any one role
- [ ] Click Authorize
- [ ] Grant the role with certain authorization pages by checking the boxes next to them
- [ ] Logout the current user
- [ ] Login the user with the role 
- [ ] That user should be able to see the authorized pages only.


## Announcement Page Checklist
1. Add Announcement Error
- [ ] Go to Shared Space -> Announcement
- [ ] Click Add Announcement
- [ ] Enter Subject, but leave Content blank
- [ ] The system should show "Required fields cannot be blank"


2. Add Announcement Success
- [ ] Go to Shared Space -> Announcement
- [ ] Click Add Announcement
- [ ] Enter Subject and Content
- [ ] The new announcement should be shown in the announcement page


3. Edit Announcement
- [ ] Go to Shared Space -> Announcement
- [ ] Click Edit button next to the announcement you choose
- [ ] The edited subject or content should be shown in the announcement page
- [ ] The "Last Update On" time should be updated to the current time

4. Delete Announcement
- [ ] Go to Shared Space -> Announcement
- [ ] Click Delete button next to the announcement you choose
- [ ] Click "Confirm"
- [ ] The announcement should be deleted from the announcement page