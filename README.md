# Hotwax Order Management System

This repository contains a Spring Boot application for **Order Management System (OMS)**.  
It implements REST APIs to handle orders, order items, and customer details.

---

## **Table of Contents**

1. [Project Overview](#project-overview)
2. [Database Schema](#database-schema)
3. [API Endpoints](#api-endpoints)
4. [Test Scenarios](#test-scenarios)
5. [Screenshots](#screenshots)
6. [Setup Instructions](#setup-instructions)

---

## **Project Overview**

- Java Spring Boot backend
- RESTful APIs using JSON
- MySQL / H2 database
- CRUD operations for Orders and Order Items
- JWT authentication (optional/bonus)

---

## **Database Schema**

**Tables:**
- Customer
- Contact_Mech
- Product
- Order_Header
- Order_Item

**Relationships:**
- Order_Header → Customer, Shipping & Billing Contact_Mech
- Order_Item → Order_Header & Product

---

## **API Endpoints**

| Operation | Method | URL | Description |
|-----------|--------|-----|-------------|
| Create Order | POST | /orders | Create new order |
| Retrieve Order | GET | /orders/{orderId} | Get order details |
| Update Order Item | PUT | /orders/{orderId}/items/{itemId} | Update item quantity/status |
| Add Order Item | POST | /orders/{orderId}/items | Add new item to order |
| Delete Order Item | DELETE | /orders/{orderId}/items/{itemId} | Remove item from order |
| Delete Order | DELETE | /orders/{orderId} | Delete entire order |

---

## **Test Scenarios**

### **Scenario 1: Create Order**
- **Customer:** John Doe
- **Shipping Contact:** 1600 Amphitheatre Parkway
- **Billing Contact:** 1 Infinite Loop
- **Items:** T-Shirt x2, Jeans x1
- **Expected:** 201 Created, response contains orderId and items

### **Scenario 2: Retrieve Order**
- **GET** `/orders/{orderId}`
- **Expected:** 200 OK, JSON contains order details and all items

### **Scenario 3: Update Order Item**
- **PUT** `/orders/{orderId}/items/{itemId}`
- **Update:** Quantity of Jeans → 2
- **Expected:** 200 OK, item quantity updated

### **Scenario 4: Add Order Item**
- **POST** `/orders/{orderId}/items`
- **Item:** Sneakers x1
- **Expected:** 201 Created, new item added

### **Scenario 5: Delete Order Item**
- **DELETE** `/orders/{orderId}/items/{itemId}`
- **Item:** T-Shirt
- **Expected:** 204 No Content, item removed

### **Scenario 6: Delete Order**
- **DELETE** `/orders/{orderId}`
- **Expected:** 204 No Content
- **Verify:** GET `/orders/{orderId}` → 404 Not Found

---

## **Screenshots**

### Scenario 1: Create Order
![Create Order](screenshots/scenario1_create_order.png)

### Scenario 2: Retrieve Order
![Retrieve Order](screenshots/scenario2_get_order.png)

### Scenario 3: Update Order Item
![Update Item](screenshots/scenario3_update_item.png)

### Scenario 4: Add Order Item
![Add Item](screenshots/scenario4_add_item.png)

### Scenario 5: Delete Order Item
![Delete Item](screenshots/scenario5_delete_item.png)

### Scenario 6: Delete Order
![Delete Order](screenshots/scenario6_delete_order.png)

> **Note:** Place all screenshot images in a folder named `screenshots/` in your repository.

---

## **Setup Instructions**

1. Clone repo:
```bash
git clone https://github.com/Snehaa244/HotwaxAssignment.git
cd HotwaxAssignment
