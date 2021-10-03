package command.medicine;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Stock;
import parser.MedicineValidator;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Delete medication based on user input given stock id.
 */

public class DeleteCommand extends Command {

    @Override
    public void execute(Ui ui, HashMap<String, String> parameters, ArrayList<Stock> stocks) {
        String[] requiredParameters = {CommandParameters.STOCK_ID};

        if (CommandSyntax.containsInvalidParameters(ui, parameters, requiredParameters, CommandSyntax.DELETE_COMMAND)) {
            return;
        }

        String stockIdToDelete = parameters.get(CommandParameters.STOCK_ID);
        if (!MedicineValidator.isValidStockId(ui, stockIdToDelete, stocks)) {
            return;
        }

        int stockId = Integer.parseInt(stockIdToDelete);
        for (Stock stock : stocks) {
            Medicine medicine = (Medicine) stock;
            if (medicine.getStockID() == stockId) {
                stocks.remove(stock);
                break;
            }
        }
        ui.print("Medication deleted: Stock_Id=" + stockId);
    }
}