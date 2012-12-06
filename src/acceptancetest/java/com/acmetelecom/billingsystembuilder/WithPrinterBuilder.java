package com.acmetelecom.billingsystembuilder;

import com.acmetelecom.printer.Printer;

public interface WithPrinterBuilder {
	public FinalBillingSystemBuilder withABillPrinter(Printer printer);
}
