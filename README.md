# Telecom Subscription and Billing System

A  Java command-line application to manage service providers, subscribers, and billing invoices.

---

##  Features

- **Service Providers:** Create, list, and manage telecom providers with configurable call, message, and data rates.
- **Subscribers:** Register subscribers, track voice call minutes, SMS count, and data usage.
- **Billing:** Generate and display invoices with usage limits, current spending, and payment due dates.
- **Provider Switching:** Allow subscribers to change their service provider when no outstanding charges remain.
- **Usage Limits:** Configure and update usage limits per subscriber to control billing.

---

##  Prerequisites

- **Java SE 8** or higher installed on your machine.
- A terminal or command prompt to compile and run Java programs.

---

##  Installation & Setup

1. **Clone the repository**
   ```sh
   git clone https://github.com/oktaykorkut/TelecomBillingSystem.git
   cd TelecomBillingSystem
   ```

2. **Compile the source files**
   ```sh
   javac *.java
   ```

3. **Run the application**
   ```sh
   java Main
   ```

Follow the interactive menu to explore creating providers, adding subscribers, recording usage, and generating invoices.

---

##  Project Structure

\`\`\`
TelecomBillingSystem/
├── Main.java             # Entry point with menu-driven UI
├── ServiceProvider.java  # Model and management for telecom providers
├── Subscriber.java       # Subscriber model with usage tracking
└── Invoice.java          # Invoice model with billing logic
\`\`\`

---

##  Class Summaries

### ServiceProvider
- **Fields:**
  - \`p_id\` (int) – Unique provider ID
  - \`p_name\` (String) – Provider name
  - \`voiceCallCost\`, \`messagingCost\`, \`internetCost\` (Double) – Rate settings
  - \`discountRatio\` (int) – Percentage discount for subscribers
  - \`subscribersList\` (List<Subscriber>) – Registered subscribers
- **Key Methods:**
  - \`addSubscriber(Subscriber s)\` – Register a new subscriber
  - \`removeSubscriber(Subscriber s)\` – Remove an existing subscriber
  - \`toString()\` – Human-readable provider summary

### Subscriber
- **Fields:**
  - \`s_id\` (int) – Unique subscriber ID
  - \`name\` (String) – Subscriber’s name
  - \`age\` (int)
  - \`isActive\` (boolean) – Account active status
  - \`textMessageCount\`, \`voiceCallMinute\`, \`internetUsage\` – Usage counters
  - \`s_provider\` (ServiceProvider) – Current provider
  - \`invoice\` (Invoice) – Current billing invoice
- **Key Methods:**
  - \`setUsageLimit(double limit)\` – Define maximum allowed usage
  - \`updateUsage(...)\` – Increment usage counters
  - \`generateInvoice()\` – Calculate charges and assign due date
  - \`changeServiceProvider(ServiceProvider newProvider)\` – Switch provider if no balance
  - \`toString()\` – Subscriber usage summary

### Invoice
- **Fields:**
  - \`usageLimit\` (double) – Usage cap
  - \`currentSpending\` (double) – Accumulated charges
  - \`lastDayToPay\` (Date) – Payment due date
- **Key Methods:**
  - \`getCurrentSpending()\`, \`getLastDayToPay()\` – Accessors
  - \`changeUsageLimit(double newLimit)\` – Update cap
  - \`toString()\` – Invoice detail summary