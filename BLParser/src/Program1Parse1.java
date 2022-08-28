import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Seunghyun Nam(nam197) Group 17
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to statement string of body of
     *          instruction at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        Reporter.assertElseFatalError(tokens.dequeue().equals("INSTRUCTION"),
                "IS not Instruction");

        String title = tokens.dequeue();
        //^The part that stores the first name appearing in the block

        Reporter.assertElseFatalError(Tokenizer.isIdentifier(title),
                "The title did not follow your identifier");
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "IS did not follow your identifier");
        //^The part that checks if the title and IS is a valid identifier.

        String primitiveInstructionTemplate = "move,turnleft,"
                + "turnright,infect,skip,halt";
        Reporter.assertElseFatalError(
                primitiveInstructionTemplate.indexOf(title) == -1,
                "Primitive instruction calls cannot be the title of"
                        + " instruction");
        //^The part that checks if the title of instruction is
        //any of primitive instruction calls.

        body.parseBlock(tokens);

        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                "END did not follow your identifier");
        Reporter.assertElseFatalError(tokens.dequeue().equals(title),
                "Error.Identifiers did not match.");
        return title;

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        Reporter.assertElseFatalError(tokens.dequeue().equals("PROGRAM"),
                "PROGRAM failed to start the program.");

        String programTitle = tokens.dequeue();

        Reporter.assertElseFatalError(Tokenizer.isIdentifier(programTitle),
                "\"PROGRAM\" failed to start the program. ");
        this.setName(programTitle);

        Reporter.assertElseFatalError(!programTitle.equals("PROGRAM"),
                "name does not match");
        Reporter.assertElseFatalError(tokens.dequeue().equals("IS"),
                "\"IS\" does not have appropriate identifier");

        Map<String, Statement> tempMap = this.newContext();

        while (tokens.front().equals("INSTRUCTION")
                && !tempMap.hasKey("INSTRUCTION")) {
            Statement tempStatement = this.newBody();
            String newName = parseInstruction(tokens, tempStatement);
            if (!tempMap.hasKey(newName)) {
                tempMap.add(newName, tempStatement);
            }
        }
        //making sure to check if the instruction already exists in the map

        this.swapContext(tempMap);
        Statement newStatement = this.newBody();

        Reporter.assertElseFatalError(tokens.dequeue().equals("BEGIN"),
                "the program should start with \"BEGIN\" ");

        newStatement.parseBlock(tokens);
        this.swapBody(newStatement);

        Reporter.assertElseFatalError(tokens.dequeue().equals("END"),
                " the program should end with \"END\" ");
        Reporter.assertElseFatalError(tokens.dequeue().equals(programTitle),
                "\"Program\" name does not match");

        Reporter.assertElseFatalError(tokens.length() == 1,
                "\"Program\" name does not match");
        //Make sure that after END there's end of the token.
        Reporter.assertElseFatalError(
                tokens.dequeue().equals(Tokenizer.END_OF_INPUT),
                "\"Program\" has the proper end of the token.");
        //This part checks if the last token is the end of the
        //input.
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
