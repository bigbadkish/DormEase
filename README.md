# DormEase - Dormitory Management System

## 📋 Project Overview
DormEase is a comprehensive console-based dormitory management system built with Java. It demonstrates Object-Oriented Programming principles including **Abstraction**, **Inheritance**, **Polymorphism**, and **Encapsulation**.

## 🎯 Features
- **Role-Based Access Control** (Dorm Manager, Maintenance Staff, Resident)
- **Room Management** with different types (Single, Double, Suite)
- **Payment Tracking** with transaction history
- **Maintenance Request System** with status tracking
- **Data Persistence** (Save/Load functionality)
- **Search & Statistics** for residents and rooms
- **Exception Handling** with custom exceptions

---

## 📁 File Structure

```
DormEase/
├── RoomAlreadyAssignedException.java
├── InvalidPaymentException.java
├── PaymentTransaction.java
├── Person.java
├── Request.java
├── CleaningRequest.java
├── RepairRequest.java
├── Resident.java
├── MaintenanceStaff.java
├── DormManager.java
├── Room.java
├── DataManager.java
├── DormEase.java (Main Class)
└── README.md
```

---

## 🚀 How to Compile and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command Line / Terminal access

### Step 1: Create Project Directory
```bash
mkdir DormEase
cd DormEase
```

### Step 2: Save All Java Files
Save all 13 Java files in the `DormEase` directory:
1. RoomAlreadyAssignedException.java
2. InvalidPaymentException.java
3. PaymentTransaction.java
4. Person.java
5. Request.java
6. CleaningRequest.java
7. RepairRequest.java
8. Resident.java
9. MaintenanceStaff.java
10. DormManager.java
11. Room.java
12. DataManager.java
13. DormEase.java

### Step 3: Compile All Files

**Option A: Compile all at once (Recommended)**
```bash
javac *.java
```

**Option B: Compile in order (if Option A fails)**
```bash
javac RoomAlreadyAssignedException.java
javac InvalidPaymentException.java
javac PaymentTransaction.java
javac Person.java
javac Request.java
javac CleaningRequest.java
javac RepairRequest.java
javac Room.java
javac Resident.java
javac MaintenanceStaff.java
javac DormManager.java
javac DataManager.java
javac DormEase.java
```

### Step 4: Run the Program
```bash
java DormEase
```

---

## 📖 User Guide

### Initial Setup
1. **Register a Resident** (Option 1)
   - Enter resident name
   - Note the assigned ID

2. **Login** (Option 0)
   - Choose role:
     - **Dorm Manager** - Manage rooms and view statistics
     - **Maintenance Staff** - Process maintenance requests
     - **Resident** - Use your registered ID and name

### Main Menu Options

| Option | Feature | Role Required |
|--------|---------|---------------|
| 0 | Login | None |
| 1 | Register Resident | None |
| 2 | View All Residents | None |
| 3 | Assign Room | Dorm Manager |
| 4 | Release Room | Dorm Manager |
| 5 | Deposit Payment | Resident |
| 6 | Submit Maintenance Request | Resident |
| 7 | Process Maintenance Request | Maintenance Staff |
| 8 | View Dashboard | Any (after login) |
| 9 | View Payment History | Resident |
| 10 | Search Resident | None |
| 11 | View Room Statistics | None |
| 12 | View Request History | None |
| 13 | Save Data | None |
| 14 | Load Data | None |
| 15 | Exit | None |

---

## 🎮 Sample Usage Flow

### Example 1: Register and Assign Room
```
1. Select Option 1 → Register Resident → Enter "John Doe"
2. Select Option 0 → Login as Dorm Manager → Enter name and any ID
3. Select Option 3 → Assign Room → Enter Resident ID: 1 → Select Room Index: 0
```

### Example 2: Deposit Payment
```
1. Select Option 0 → Login as Resident → Enter your registered name and ID
2. Select Option 5 → Deposit Payment → Enter amount: 5000
3. Select Option 9 → View Payment History
```

### Example 3: Submit and Process Request
```
1. Login as Resident
2. Select Option 6 → Submit Request → Type "cleaning" → Describe issue
3. Logout and Login as Maintenance Staff
4. Select Option 7 → Select request index → Process
```

---

## 🏗️ OOP Principles Demonstrated

### 1. **Abstraction**
- Abstract class `Person` with abstract method `viewDashboard()`
- Abstract class `Request` with abstract method `processRequest()`

### 2. **Inheritance**
- `Resident`, `MaintenanceStaff`, `DormManager` extend `Person`
- `CleaningRequest`, `RepairRequest` extend `Request`

### 3. **Polymorphism**
- Method overriding: `viewDashboard()` in all Person subclasses
- Method overriding: `processRequest()` in all Request subclasses

### 4. **Encapsulation**
- Private fields with public getters/setters
- Data hiding in `Room`, `Resident`, `PaymentTransaction`

---

## 💾 Data Persistence

The system can save and load data using Java serialization:
- **Save Data** (Option 13): Creates `dormease_data.dat`
- **Load Data** (Option 14): Restores previous session

**Note**: Data file is created in the same directory as the program.

---

## 🔧 Troubleshooting

### Compilation Errors
**Error**: "class not found"
- **Solution**: Make sure all 13 .java files are in the same directory

**Error**: "javac is not recognized"
- **Solution**: Install JDK and add it to your system PATH

### Runtime Errors
**Error**: "Invalid Resident ID"
- **Solution**: Register a resident first before logging in as resident

**Error**: "Access denied"
- **Solution**: Login with the appropriate role for the feature

---

## 📊 System Architecture

```
Person (Abstract)
├── Resident
├── MaintenanceStaff
└── DormManager

Request (Abstract)
├── CleaningRequest
└── RepairRequest

Other Classes:
- Room
- PaymentTransaction
- DataManager (Utility)
- Custom Exceptions
```

---

## 👥 User Roles & Permissions

### Dorm Manager
✅ Assign rooms to residents  
✅ Release rooms from residents  
✅ View all statistics  

### Maintenance Staff
✅ Process cleaning requests  
✅ Process repair requests  
✅ View request history  

### Resident
✅ Deposit payments  
✅ Submit maintenance requests  
✅ View payment history  
✅ View personal dashboard  

---

## 📝 Notes

1. **Pre-loaded Rooms**: System starts with 5 sample rooms (A101, A102, B201, B202, C301)
2. **Room Types**: Single (PHP 5,000/mo), Double (PHP 7,500/mo), Suite (PHP 12,000/mo)
3. **Request Types**: Only "cleaning" and "repair" are valid
4. **Data Persistence**: Remember to save before exiting if you want to keep your data

---

## 🐛 Known Limitations

- Console-based interface (no GUI)
- Single-user session (no concurrent users)
- No password protection for login
- Data file is not encrypted

---

## 📧 Support

For issues or questions:
1. Check this README thoroughly
2. Verify all files are present
3. Ensure Java version compatibility (Java 8+)
4. Try recompiling all files

---

## 📜 License

This project is created for educational purposes demonstrating OOP principles in Java.

---

**Version**: 2.0  
**Last Updated**: 2025  
**Author**: DormEase Development Team

---

## 🎓 Academic Compliance

This project demonstrates:
- ✅ **Abstraction** - Abstract classes with abstract methods
- ✅ **Inheritance** - Multi-level class hierarchy
- ✅ **Polymorphism** - Method overriding
- ✅ **Encapsulation** - Private fields with getters/setters
- ✅ **Exception Handling** - Custom exceptions
- ✅ **Collections Framework** - HashMap, ArrayList
- ✅ **File I/O** - Serialization for data persistence
- ✅ **Input Validation** - Robust error handling

---

## 🚀 Quick Start Commands

```bash
# Clone or create directory
mkdir DormEase && cd DormEase

# Compile
javac *.java

# Run
java DormEase

# Clean compiled files (optional)
rm *.class
```

---

**Happy Coding! 🎉**