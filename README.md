

# DormEase - Simplified Version

## 📋 Overview
A streamlined dormitory management system demonstrating core OOP principles in Java.

---

## 📁 Files (Only 7 Files!)

```
DormEase/
├── Person.java                    (Abstract - Abstraction)
├── Request.java                   (Abstract - Abstraction)
├── CleaningRequest.java           (Inheritance, Polymorphism)
├── RepairRequest.java             (Inheritance, Polymorphism)
├── Resident.java                  (Inheritance, Encapsulation)
├── MaintenanceStaff.java          (Inheritance, Polymorphism)
├── DormManager.java               (Inheritance, Polymorphism)
└── DormEase.java                  (Main Class)
```

---

## ✅ OOP Requirements Met

| Principle | Implementation |
|-----------|---------------|
| **Abstraction** | `Person` and `Request` abstract classes |
| **Inheritance** | 5 classes extend abstract classes |
| **Polymorphism** | `viewDashboard()` and `processRequest()` overridden |
| **Encapsulation** | Private fields with getters/setters in `Resident` |

---

## 🚀 Quick Start

### 1. Save all 7 files in one folder

### 2. Compile
```bash
javac *.java
```

### 3. Run
```bash
java DormEase
```

---

## 📖 How to Use

### Step 1: Register a Resident
```
Menu → 1 → Enter name → Note the ID
```

### Step 2: Login
```
Menu → 2 → Select role → Enter name and ID
```

### Step 3: Use Features Based on Role

**Dorm Manager:**
- Assign Room (Option 3)
- Release Room (Option 4)

**Resident:**
- Deposit Payment (Option 5)
- Submit Request (Option 6)

**Maintenance Staff:**
- Process Request (Option 7)

---

## 🎯 Menu Options

| # | Feature | Access |
|---|---------|--------|
| 1 | Register Resident | Anyone |
| 2 | Login | Anyone |
| 3 | Assign Room | Manager |
| 4 | Release Room | Manager |
| 5 | Deposit Payment | Resident |
| 6 | Submit Request | Resident |
| 7 | Process Request | Staff |
| 8 | View Dashboard | Logged in users |
| 9 | View All Residents | Anyone |
| 0 | Exit | Anyone |

---

## 💡 Sample Usage

### Example 1: Complete Resident Flow
```
1. Register → Enter "John Doe" → Get ID 1
2. Login as Manager → Any name/ID
3. Assign Room → Resident ID: 1 → Room: 0 (A101)
4. Login as Resident → Name: John Doe, ID: 1
5. Deposit → Amount: 5000
6. Submit Request → Type: cleaning → Desc: "Clean room"
```

### Example 2: Process Request
```
1. Login as Maintenance Staff → Any name/ID
2. Process Request → Select index 0
```

---

## 🏗️ Class Structure

```
Abstract Classes:
  Person ─┬─ Resident
          ├─ MaintenanceStaff
          └─ DormManager

  Request ─┬─ CleaningRequest
           └─ RepairRequest
```

---

## 🔧 Troubleshooting

**Error: "javac not found"**
- Install JDK and add to PATH

**Error: "Invalid Resident ID"**
- Register a resident first (Option 1)
- Use the exact ID shown after registration

**Error: "Access denied"**
- Login with correct role for that feature

---

## 📊 Features Summary

✅ Role-based access control  
✅ Room assignment system  
✅ Payment tracking  
✅ Maintenance requests  
✅ Dashboard views  
✅ All OOP principles demonstrated  
✅ Simple & clean code  

---

## 🎓 What This Code Demonstrates

### 1. Abstraction
- `Person` has abstract method `viewDashboard()`
- `Request` has abstract method `processRequest()`

### 2. Inheritance
- `Resident`, `MaintenanceStaff`, `DormManager` extend `Person`
- `CleaningRequest`, `RepairRequest` extend `Request`

### 3. Polymorphism
- Each subclass overrides abstract methods differently
- Same method name, different implementations

### 4. Encapsulation
- `Resident` has private `room` and `balance` fields
- Accessed only through public getters/setters

---

## 📝 Key Simplifications

❌ **Removed:**
- Complex exception handling
- Data persistence (save/load)
- Payment history tracking
- HashMap collections
- Multiple utility classes
- Serialization

✅ **Kept:**
- All 4 OOP principles
- Core functionality
- Clean, readable code
- Easy to understand

---

## 🎯 Perfect For

- OOP assignments
- Learning Java basics
- Understanding inheritance
- Quick demonstrations
- Academic requirements

---

## 📧 Notes

- **Preloaded Rooms**: A101, A102, B201, B202, C301
- **Login**: Manager/Staff can use any name/ID, Residents must use registered credentials
- **No Data Persistence**: Data resets when program exits

---

## 🚀 Compile & Run (One-liner)

```bash
javac *.java && java DormEase
```

---

**Version**: 2.0 Simplified  
**Files**: 7 (instead of 13)  
**Lines**: ~400 (instead of ~800)  
**Same Requirements**: ✅ All OOP principles met

---

**Happy Coding! 🎉**