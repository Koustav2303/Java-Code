/**
 * PROBLEM: Design Vending Machine
 * * Architect a modular, production-ready State Machine for a business Vending Machine workflow.
 * The system handles state changes cleanly without massive switch blocks or messy if-statements.
 * * Strategy: Structural Java `enum` Instance Enumeration
 * We leverage native Java `enum` strategies to explicitly handle behaviors for distinct states 
 * (IDLE, HAS_COIN, DISPENSING, OUT_OF_STOCK). Enums function as abstract strategy frameworks, 
 * confining data change transitions strictly to valid, well-defined rules.
 * * Complexity:
 * Time Complexity: O(1) state lookup routing actions.
 * Space Complexity: O(1) static type footprints.
 */
public class DesignVendingMachine {

    // Structural Enumeration representing individual State Behaviors
    enum MachineState {
        IDLE {
            @Override void insertCoin(VendingMachine ctx) {
                System.out.println("Coin accepted.");
                ctx.setState(HAS_COIN);
            }
            @Override void pressButton(VendingMachine ctx) { System.out.println("Insert coin first."); }
            @Override void dispense(VendingMachine ctx) { System.out.println("Payment required."); }
        },
        HAS_COIN {
            @Override void insertCoin(VendingMachine ctx) { System.out.println("Coin already present."); }
            @Override void pressButton(VendingMachine ctx) {
                System.out.println("Processing selection...");
                ctx.setState(DISPENSING);
                ctx.getState().dispense(ctx); // Direct logical execution step
            }
            @Override void dispense(VendingMachine ctx) { System.out.println("Press option button first."); }
        },
        DISPENSING {
            @Override void insertCoin(VendingMachine ctx) { System.out.println("Wait! Currently dispensing item."); }
            @Override void pressButton(VendingMachine ctx) { System.out.println("Already handling execution request."); }
            @Override void dispense(VendingMachine ctx) {
                ctx.decrementInventory();
                System.out.println("Item released to tray.");
                if (ctx.getInventoryCount() > 0) {
                    ctx.setState(IDLE);
                } else {
                    ctx.setState(OUT_OF_STOCK);
                }
            }
        },
        OUT_OF_STOCK {
            @Override void insertCoin(VendingMachine ctx) { System.out.println("Machine empty. Ejecting coin."); }
            @Override void pressButton(VendingMachine ctx) { System.out.println("Items empty."); }
            @Override void dispense(VendingMachine ctx) { System.out.println("Defect out of stock state."); }
        };

        abstract void insertCoin(VendingMachine ctx);
        abstract void pressButton(VendingMachine ctx);
        abstract void dispense(VendingMachine ctx);
    }

    static class VendingMachine {
        private MachineState currentState = MachineState.IDLE;
        private int inventory = 2;

        public void setState(MachineState state) { this.currentState = state; }
        public MachineState getState() { return currentState; }
        public int getInventoryCount() { return inventory; }
        public void decrementInventory() { if (inventory > 0) inventory--; }

        // Core workflow triggers routed to our structural enumeration interface handlers
        public void insertCoin() { currentState.insertCoin(this); }
        public void pressButton() { currentState.pressButton(this); }
    }

    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        System.out.println("Initial State: " + vm.getState()); // IDLE
        vm.pressButton(); // Try hacking flow directly -> Triggers protection text warning
        
        vm.insertCoin();  // IDLE -> Transition to HAS_COIN
        vm.pressButton(); // HAS_COIN -> DISPENSING -> IDLE (inventory is now 1)
        System.out.println("Ending State status: " + vm.getState()); // IDLE
    }
}